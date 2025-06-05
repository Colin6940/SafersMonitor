# SAFERS Monitor

**산업현장 및 취약현장 근로자 안전 관리 통합 시스템**

- [SafersMonitor master 리포지토리](https://github.com/Colin6940/SafersMonitor)
- 주요 기여자  
  - 이현규([Colin6940](https://github.com/Colin6940))  
  - 윤영찬([yoon1104](https://github.com/yoon1104))  
  - 전성현([whiteblack1858](https://github.com/whiteblack1858))  
  - 하윤승([Sol1470](https://github.com/Sol1470))  
  - 암카([amgalan976](https://github.com/amgalan976))  



## 소개

**SAFERS Monitor**는 산업현장, 공사장, 노인/취약계층 등 다양한 환경에서  
작업자와 근로자의 안전을 실시간으로 모니터링하고,  
이상 상황을 감지하면 관리자에게 즉각 알림을 전송하는 통합 안전관리 시스템입니다.  



## 주요 기능

### 📱 앱(Android/Watch)

- 실시간 심박수, 가속도, 위치 등 센서 기반 안전 모니터링
- 충격, 무활동, 이상 징후 자동 감지 및 관리자 알림 발송
- 관리자(비상연락처) 다중 등록 및 자동 문자 전송
- 사용자 맞춤 민감도 슬라이더 UI 제공
- QR 코드 기반 간편 로그인
- Foreground Service 및 백그라운드 동작 지원
- 워치 연동: 심박, 움직임, 충격 실시간 감지 및 연동

### 🖥️ 웹 관리자 페이지

- 관리자 로그인 및 회원가입
- 실시간 대시보드: 근로자 상태 모니터링, 위험 신호 실시간 표시
- 근로자 정보 등록, 수정, 삭제, 검색
- 이벤트 로그/알림 내역 확인
- 근로자 위치 지도 표시
- 비상 연락처 관리
- QR코드 로그인 연동

### ⚡ AI 이상상황 분석 (Gemini API 연동)

- **Google Gemini API**와 연동하여, 실시간으로 수집된 심박수·가속도·무활동 등 센서 데이터를 AI로 분석
- AI가 작업자 건강 상태, 위험 신호, 사고 유형을 자동 분류 및 예측
- 관리자 대시보드에서 분석 결과 실시간 확인 가능
- Gemini API 최신 LLM을 이용한 심층 패턴 인식 및 이상상황 자동 분류

### 🛠️ 백엔드/API

- FastAPI 기반 서버
- PostgreSQL 데이터베이스 연동
- RESTful API 제공
- 실시간 데이터 수집/저장/분석 및 알림 연동
- Firebase Functions(일부 기능)




## 프로젝트 구조
   ```
SafersMonitor/
├── app/ # Android/Watch 앱 소스
├── web/ # React 웹 관리자 페이지
├── backend/ # FastAPI 서버 (Gemini API 연동 포함)
├── db/ # PostgreSQL 스키마 및 데이터
├── functions/ # Firebase functions
└── README.md
   ```


## 설치 및 실행 방법

1. **백엔드 실행**  
   ```bash
   cd backend
   pip install -r requirements.txt
   uvicorn main:app --reload
   ```
   
2. **웹 프론트엔드 실행**
   ```bash
   cd web
   npm install
   npm start
   ```

4. **앱 실행**
  안드로이드 스튜디오 이용해 빌드


## 커밋 히스토리 요약

- **4/17**  
  - 노인 보호 센서앱 최초 업로드 → 피드백 후 산업현장 안전 프로젝트로 전환

- **5/4**  
  - [main/커밋1] 산업현장용 프로젝트로 git 재등록 (작성자: Colin6940)

- **5/5**  
  - [main/커밋2] 관리자웹(로그인, 대시보드), DB(기본기능), 앱 기초 (Colin6940)  
  - [main/커밋3] 앱 오류 수정(해결 불가, 버전오류 판명) (Colin6940)

- **5/6**  
  - [unfinished/커밋1] 스마트폰앱 처음부터 재개발, 관리자웹·DB 변경 없음 (Colin6940)

- **5/11**  
  - [main/커밋4] unfinished 병합, safersapp 폴더 삭제, 브랜치 삭제 (Colin6940)

- **5/18**  
  - [main/커밋5] 잘못된 내용 롤백 (Colin6940)

- **5/28**  
  - [main/커밋6] firebase functions, QR 로그인 리팩토링 (Colin6940)

- **5/29**  
  - [app/커밋1] 워치앱·스마트폰앱 새로 빌드, 두 앱 간의 연동 완성 (whiteblack1858)

- **6/5**  
  - [ai/커밋1] AI 모듈 및 로직 추가, Gemini API 실시간 이상상황 분석 기능 구현 (Sol1470)

- **6/6**  
  - [main/커밋7] 편집/삭제 API 추가 (Colin6940)  
  - [main/커밋8] app/ai 브랜치 메인 병합 (Colin6940)




## 기술 스택

- **프론트엔드**  
  - React  
  - Material-UI  

- **백엔드**  
  - FastAPI  
  - Python  
  - PostgreSQL  
  - Firebase Functions  

- **모바일**  
  - Android (Kotlin)  
  - Wear OS  
  - Google Health Services  

- **AI 분석**  
  - Google Gemini API  

- **기타**  
  - JWT 인증  
  - QR 코드 로그인  
  - RESTful API  

---

## 라이선스

MIT License
