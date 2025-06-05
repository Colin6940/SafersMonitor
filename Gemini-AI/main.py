import google.generativeai as genai
import time
import json
from collections import deque
from datetime import datetime, timedelta
from typing import List, Dict, Optional, Tuple
import statistics

class HeartRateFallDetection:
    def __init__(self, api_key: str):
        """
        심박수 기반 낙상 감지 시스템 초기화
        
        Args:
            api_key: Gemini API 키
        """
        # Gemini API 설정
        genai.configure(api_key=api_key)
        self.model = genai.GenerativeModel('gemini-1.5-flash')
        
        # 심박수 데이터 저장 (최근 5분간)
        self.hr_data = deque(maxlen=300)  # 5분 * 60초
        self.timestamps = deque(maxlen=300)
        
        # 상태 변수
        self.baseline_hr = 70  # 초기 기준 심박수
        self.last_fall_time = None
        self.is_resting = True
        
        # 임계값 설정
        self.thresholds = {
            'fall_increase': 18,  # 낙상 후 증가 임계값 (%)
            'fall_decrease': -12,  # 낙상 후 감소 임계값 (%)
            'danger_high': 220,   # 위험 높음
            'warning_high': 200,  # 주의 높음
            'warning_low_min': 46,  # 주의 낮음 최소
            'warning_low_max': 60,  # 주의 낮음 최대
            'danger_low': 45,     # 위험 낮음
            'danger_low_with_fall': 50  # 낙상 시 위험 낮음
        }
    
    def add_heart_rate(self, hr: int, fall_flag: int = 0) -> Dict:
        """
        새로운 심박수 데이터 추가 및 분석
        
        Args:
            hr: 현재 심박수
            fall_flag: 낙상 감지 플래그 (0: 없음, 1: 있음)
        
        Returns:
            분석 결과 딕셔너리
        """
        current_time = datetime.now()
        
        # 데이터 추가
        self.hr_data.append(hr)
        self.timestamps.append(current_time)
        
        # 기준 심박수 업데이트 (최근 30초 평균)
        self._update_baseline_hr()
        
        # 분석 수행
        analysis_result = self._analyze_heart_rate(hr, fall_flag, current_time)
        
        # Gemini API를 통한 추가 분석
        ai_analysis = self._get_ai_analysis(hr, fall_flag, analysis_result)
        analysis_result['ai_recommendation'] = ai_analysis
        
        return analysis_result
    
    def _update_baseline_hr(self):
        """최근 30초간 심박수 평균으로 기준 심박수 업데이트"""
        if len(self.hr_data) < 2:
            return
        
        current_time = self.timestamps[-1]
        thirty_seconds_ago = current_time - timedelta(seconds=30)
        
        # 최근 30초 데이터 필터링
        recent_hr = []
        for i, timestamp in enumerate(self.timestamps):
            if timestamp >= thirty_seconds_ago:
                recent_hr.append(self.hr_data[i])
        
        if recent_hr:
            self.baseline_hr = statistics.mean(recent_hr)
    
    def _analyze_heart_rate(self, current_hr: int, fall_flag: int, current_time: datetime) -> Dict:
        """심박수 분석 수행"""
        result = {
            'timestamp': current_time.isoformat(),
            'current_hr': current_hr,
            'baseline_hr': round(self.baseline_hr, 1),
            'fall_detected': bool(fall_flag),
            'status': 'normal',
            'alert_level': 'none',
            'message': '',
            'change_ratio': 0
        }
        
        # 변화율 계산
        if self.baseline_hr > 0:
            change_ratio = (current_hr - self.baseline_hr) / self.baseline_hr * 100
            result['change_ratio'] = round(change_ratio, 2)
        
        # 낙상 감지된 경우
        if fall_flag == 1:
            self.last_fall_time = current_time
            result.update(self._analyze_fall_case(current_hr, change_ratio))
        
        # 절대값 기반 위험 감지
        abs_analysis = self._analyze_absolute_values(current_hr, fall_flag)
        if abs_analysis['alert_level'] != 'none':
            result.update(abs_analysis)
        
        # 낙상 없이도 위험 징후 포착
        if fall_flag == 0:
            no_fall_analysis = self._analyze_without_fall(current_hr, change_ratio)
            if no_fall_analysis['alert_level'] != 'none':
                result.update(no_fall_analysis)
        
        return result
    
    def _analyze_fall_case(self, hr: int, change_ratio: float) -> Dict:
        """낙상 감지 시 분석"""
        if change_ratio >= self.thresholds['fall_increase']:
            return {
                'status': 'fall_confirmed',
                'alert_level': 'danger',
                'message': f'실제 낙상 감지 - 교감신경 반응 (심박수 {change_ratio:+.1f}% 증가)'
            }
        elif change_ratio <= self.thresholds['fall_decrease']:
            return {
                'status': 'fall_confirmed',
                'alert_level': 'danger',
                'message': f'실제 낙상 감지 - 쇼크/실신 반응 (심박수 {change_ratio:+.1f}% 감소)'
            }
        else:
            return {
                'status': 'false_positive',
                'alert_level': 'info',
                'message': '낙상 오탐 가능성 - 심박수 변화 미미'
            }
    
    def _analyze_absolute_values(self, hr: int, fall_flag: int) -> Dict:
        """절대값 기반 위험도 분석"""
        if hr >= self.thresholds['danger_high']:
            return {
                'status': 'critical_high',
                'alert_level': 'danger',
                'message': f'🚨 위험: 비정상적으로 높은 심박수 ({hr}bpm)'
            }
        elif hr >= self.thresholds['warning_high']:
            return {
                'status': 'warning_high',
                'alert_level': 'warning',
                'message': f'⚠️ 주의: 과도한 심박수 상승 ({hr}bpm)'
            }
        elif hr <= self.thresholds['danger_low_with_fall'] and fall_flag == 1:
            return {
                'status': 'critical_low_fall',
                'alert_level': 'danger',
                'message': f'🚨 위험: 낙상 후 심박수 급감 - 실신/사고 가능성 ({hr}bpm)'
            }
        elif self.thresholds['warning_low_min'] <= hr <= self.thresholds['warning_low_max']:
            return {
                'status': 'warning_low',
                'alert_level': 'warning',
                'message': f'⚠️ 주의: 비정상적 심박수 저하 ({hr}bpm)'
            }
        elif hr < self.thresholds['danger_low']:
            return {
                'status': 'critical_low',
                'alert_level': 'danger',
                'message': f'🚨 위험: 실신/심정지 가능성 ({hr}bpm)'
            }
        
        return {'alert_level': 'none'}
    
    def _analyze_without_fall(self, hr: int, change_ratio: float) -> Dict:
        """낙상 감지 없이 위험 징후 분석"""
        if change_ratio >= self.thresholds['fall_increase'] or hr > self.thresholds['warning_high']:
            return {
                'status': 'suspicious_high',
                'alert_level': 'warning',
                'message': f'낙상 없음 - 생리적 이상 징후: 심박수 급상승 ({change_ratio:+.1f}%)'
            }
        elif change_ratio <= self.thresholds['fall_decrease'] or hr < self.thresholds['danger_low']:
            return {
                'status': 'suspicious_low',
                'alert_level': 'warning',
                'message': f'낙상 없음 - 생리적 이상 징후: 심박수 급감 ({change_ratio:+.1f}%)'
            }
        
        return {'alert_level': 'none'}
    
    def _get_ai_analysis(self, hr: int, fall_flag: int, analysis_result: Dict) -> str:
        """Gemini API를 통한 AI 분석"""
        try:
            prompt = f"""
            심박수 모니터링 데이터 분석:
            
            현재 심박수: {hr}bpm
            기준 심박수: {analysis_result['baseline_hr']}bpm
            변화율: {analysis_result['change_ratio']}%
            낙상 감지: {'예' if fall_flag else '아니오'}
            현재 상태: {analysis_result['status']}
            경고 수준: {analysis_result['alert_level']}
            
            위 데이터를 바탕으로:
            1. 현재 상황에 대한 종합적인 평가
            2. 추가 모니터링이 필요한 부분
            3. 권장 조치사항
            
            간결하고 실용적인 조언을 제공해주세요.
            """
            
            response = self.model.generate_content(prompt)
            return response.text
            
        except Exception as e:
            return f"AI 분석 중 오류 발생: {str(e)}"
    
    def get_health_status_summary(self) -> Dict:
        """장기적 건강 상태 요약"""
        if len(self.hr_data) < 60:  # 최소 1분 데이터 필요
            return {"message": "충분한 데이터가 없습니다"}
        
        current_time = self.timestamps[-1]
        
        # 최근 1분, 5분 평균
        one_min_ago = current_time - timedelta(minutes=1)
        five_min_ago = current_time - timedelta(minutes=5)
        
        recent_1min = [self.hr_data[i] for i, t in enumerate(self.timestamps) if t >= one_min_ago]
        recent_5min = [self.hr_data[i] for i, t in enumerate(self.timestamps) if t >= five_min_ago]
        
        summary = {
            'current_time': current_time.isoformat(),
            'baseline_hr': round(self.baseline_hr, 1),
            'avg_1min': round(statistics.mean(recent_1min), 1) if recent_1min else 0,
            'avg_5min': round(statistics.mean(recent_5min), 1) if recent_5min else 0,
            'total_measurements': len(self.hr_data)
        }
        
        return summary

# 사용 예제
def main():
    # API 키로 시스템 초기화
    detector = HeartRateFallDetection("AIzaSyBbsNPE14EO-tLzfF9iB39X0sBHz46KQyM")
    
    # 테스트 시나리오 - 다양한 상황 시뮬레이션
    test_scenarios = [
        # === 정상 상태 ===
        (65, 0),   # 정상 - 안정 시
        (70, 0),   # 정상 - 기준값
        (75, 0),   # 정상 - 약간 상승
        (78, 0),   # 정상 - 가벼운 활동
        (72, 0),   # 정상 - 안정 복귀
        
        # === 가벼운 운동/활동 ===
        (85, 0),   # 가벼운 활동
        (95, 0),   # 보통 활동
        (110, 0),  # 활발한 활동
        (125, 0),  # 운동 중
        (90, 0),   # 운동 후 회복
        
        # === 낙상 + 교감신경 반응 (심박수 증가) ===
        (85, 1),   # 낙상 + 약간 증가 (기준선 대비 +20%)
        (95, 1),   # 낙상 + 중간 증가 (+35%)
        (105, 1),  # 낙상 + 상당한 증가 (+50%)
        (120, 1),  # 낙상 + 큰 증가 (+70%)
        (140, 1),  # 낙상 + 매우 큰 증가 (+100%)
        
        # === 낙상 + 쇼크/실신 반응 (심박수 감소) ===
        (62, 1),   # 낙상 + 약간 감소 (-12%)
        (58, 1),   # 낙상 + 중간 감소 (-18%)
        (52, 1),   # 낙상 + 상당한 감소 (-26%)
        (45, 1),   # 낙상 + 큰 감소 (-36%)
        (40, 1),   # 낙상 + 위험한 감소 (-43%)
        
        # === 낙상 오탐 케이스 ===
        (73, 1),   # 낙상 감지되었지만 심박수 변화 미미 (+4%)
        (68, 1),   # 낙상 감지되었지만 심박수 변화 미미 (-3%)
        (76, 1),   # 낙상 감지되었지만 심박수 변화 미미 (+8%)
        (65, 1),   # 낙상 감지되었지만 심박수 변화 미미 (-7%)
        
        # === 절대값 기반 위험 상황 ===
        (220, 0),  # 위험 - 최고 임계값
        (225, 0),  # 위험 - 최고 임계값 초과
        (210, 0),  # 주의 - 높은 심박수
        (205, 0),  # 주의 - 높은 심박수
        (45, 0),   # 위험 - 낮은 심박수 (실신 위험)
        (42, 0),   # 위험 - 매우 낮은 심박수
        (55, 0),   # 주의 - 비정상적 저하
        (48, 0),   # 주의 - 비정상적 저하
        
        # === 낙상 없이 생리적 이상 징후 ===
        (95, 0),   # 심박수 급상승 (+35%) - 기계사고 의심
        (105, 0),  # 심박수 급상승 (+50%) - 전기쇼크 의심
        (120, 0),  # 심박수 급상승 (+70%) - 공포반응 의심
        (58, 0),   # 심박수 급감 (-18%) - 실신 전조 의심
        (52, 0),   # 심박수 급감 (-26%) - 산소결핍 의심
        (46, 0),   # 심박수 급감 (-34%) - 독성노출 의심
        
        # === 특수 상황 시뮬레이션 ===
        (180, 1),  # 낙상 + 극도의 스트레스 반응
        (35, 1),   # 낙상 + 심각한 쇼크 상태
        (250, 0),  # 기계 오작동 또는 극한 상황
        (30, 0),   # 심정지 위험 상태
        
        # === 경계값 테스트 ===
        (200, 0),  # 주의 임계값 정확히
        (201, 0),  # 주의 임계값 1 초과
        (219, 0),  # 위험 임계값 1 미만
        (221, 0),  # 위험 임계값 1 초과
        (60, 0),   # 주의 낮음 임계값 정확히
        (61, 0),   # 주의 낮음 임계값 1 초과
        (46, 0),   # 주의 낮음 임계값 정확히
        (44, 0),   # 위험 낮음 임계값 1 미만
        
        # === 회복 패턴 시뮬레이션 ===
        (140, 1),  # 낙상 후 높은 심박수
        (120, 0),  # 점진적 회복
        (100, 0),  # 계속 회복
        (85, 0),   # 거의 정상
        (75, 0),   # 정상 복귀
        
        # === 연속적 변화 패턴 ===
        (70, 0),   # 안정 상태
        (85, 0),   # 점진적 증가
        (100, 0),  # 계속 증가
        (115, 0),  # 더 증가
        (130, 0),  # 높은 수준
        (145, 0),  # 매우 높은 수준
        (160, 1),  # 낙상 발생
        (175, 0),  # 낙상 후 더 증가
        (150, 0),  # 점진적 감소
        (125, 0),  # 계속 감소
        (100, 0),  # 더 감소
        (80, 0),   # 거의 정상
        (72, 0),   # 완전 회복
        
        # === 부정맥 시뮬레이션 ===
        (45, 0),   # 서맥
        (180, 0),  # 빈맥
        (50, 0),   # 다시 서맥
        (170, 0),  # 다시 빈맥
        (75, 0),   # 정상 복귀
        
        # === 야간/수면 패턴 ===
        (55, 0),   # 야간 낮은 심박수
        (50, 0),   # 수면 중 더 낮음
        (52, 0),   # 약간 상승
        (48, 0),   # 다시 감소
        (85, 0),   # 갑작스런 각성
        (70, 0),   # 정상 복귀
    ]
    
    print("=== 심박수 기반 낙상 감지 시스템 테스트 ===\n")
    
    for i, (hr, fall_flag) in enumerate(test_scenarios, 1):
        print(f"[테스트 {i}] 심박수: {hr}bpm, 낙상감지: {fall_flag}")
        
        result = detector.add_heart_rate(hr, fall_flag)
        
        print(f"  상태: {result['status']}")
        print(f"  경고수준: {result['alert_level']}")
        print(f"  메시지: {result['message']}")
        print(f"  변화율: {result['change_ratio']}%")
        print(f"  AI 분석: {result['ai_recommendation'][:100]}...")
        print("-" * 60)
        
        time.sleep(1)  # API 호출 간격
    
    # 건강 상태 요약
    summary = detector.get_health_status_summary()
    print("\n=== 건강 상태 요약 ===")
    print(json.dumps(summary, indent=2, ensure_ascii=False))

if __name__ == "__main__":
    main()