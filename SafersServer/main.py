from fastapi import FastAPI, Depends, HTTPException
from fastapi.security import OAuth2PasswordBearer
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import jwt
import psycopg2
from datetime import datetime, timedelta
import traceback
import bcrypt
import qrcode
import uuid
import io
import base64

# ==============================
# 초기 설정
# ==============================

app = FastAPI()

# CORS 허용 (React 프론트엔드 연동)
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

SECRET_KEY = "your_secret_key"
TOKEN_EXPIRATION_MINUTES = 60

oauth2_scheme = OAuth2PasswordBearer(tokenUrl="/api/login")

# ==============================
# DB 연결
# ==============================
def get_db():
    print("[DEBUG] get_db() 호출됨")
    try:
        conn = psycopg2.connect(
            dbname="wearsafers_db",
            user="admin",
            password="123456",  # 비밀번호 여기에 수정
            host="127.0.0.1",
            port=5432,
            options='-c client_encoding=UTF8 -c lc_messages=C'
        )
        print("[DEBUG] DB 연결 성공")
        return conn
    except Exception as e:
        print("[ERROR] DB 연결 실패:", e)
        traceback.print_exc()
        raise

# ==============================
# 테이블 생성 함수
# ==============================
def create_tables():
    print("[DEBUG] create_tables() 시작")
    conn = get_db()
    cur = conn.cursor()
    try:
        cur.execute("""
            CREATE TABLE IF NOT EXISTS users (
                user_id SERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                phone_number VARCHAR(20) UNIQUE NOT NULL,
                id_number VARCHAR(20),
                address VARCHAR(255),
                gender VARCHAR(10),
                emergency_contact VARCHAR(20),
                blood_type VARCHAR(3),
                known_diseases TEXT,
                medications TEXT,
                allergies TEXT,
                height FLOAT,
                weight FLOAT,
                safety_training_completed BOOLEAN DEFAULT FALSE,
                license_info TEXT,
                profile_image_url VARCHAR(255),
                device_id VARCHAR(50),
                affiliation VARCHAR(100),
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
        """)
        print("[DEBUG] users 테이블 생성됨")

        cur.execute("""
            CREATE TABLE IF NOT EXISTS admins (
                admin_id SERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                username VARCHAR(50) UNIQUE NOT NULL,
                password_hash VARCHAR(255) NOT NULL,
                id_number VARCHAR(20),
                profile_image_url VARCHAR(255),
                affiliation VARCHAR(100),
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
        """)
        print("[DEBUG] admins 테이블 생성됨")

        cur.execute("""
            CREATE TABLE IF NOT EXISTS realtime_data (
                data_id SERIAL PRIMARY KEY,
                user_id INT REFERENCES users(user_id),
                heart_rate FLOAT,
                acceleration FLOAT,
                steps INT,
                latitude DOUBLE PRECISION,
                longitude DOUBLE PRECISION,
                timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
        """)
        print("[DEBUG] realtime_data 테이블 생성됨")

        cur.execute("""
            CREATE TABLE IF NOT EXISTS health_records (
                record_id SERIAL PRIMARY KEY,
                user_id INT REFERENCES users(user_id),
                blood_pressure VARCHAR(20),
                ecg_result VARCHAR(100),
                spo2 FLOAT,
                timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
        """)
        print("[DEBUG] health_records 테이블 생성됨")

        cur.execute("""
            CREATE TABLE IF NOT EXISTS alerts (
                alert_id SERIAL PRIMARY KEY,
                user_id INT REFERENCES users(user_id),
                data_id INT REFERENCES realtime_data(data_id),
                detected_abnormal BOOLEAN,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                notified BOOLEAN DEFAULT FALSE,
                notified_at TIMESTAMP
            );
        """)
        print("[DEBUG] alerts 테이블 생성됨")

        cur.execute("""
            CREATE TABLE IF NOT EXISTS login_logs (
                log_id SERIAL PRIMARY KEY,
                user_id INT REFERENCES users(user_id),
                admin_id INT REFERENCES admins(admin_id),
                login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                ip_address VARCHAR(50)
            );
        """)
        print("[DEBUG] login_logs 테이블 생성됨")

        cur.execute("""
            CREATE TABLE IF NOT EXISTS auth_tokens (
                token_id SERIAL PRIMARY KEY,
                user_id INT REFERENCES users(user_id),
                admin_id INT REFERENCES admins(admin_id),
                token VARCHAR(255) UNIQUE NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                expires_at TIMESTAMP
            );
        """)
        print("[DEBUG] auth_tokens 테이블 생성됨")

        conn.commit()
        print("[DEBUG] 모든 테이블 생성 완료")
    except Exception as e:
        print("[ERROR] 테이블 생성 실패:", e)
        traceback.print_exc()
    finally:
        cur.close()
        conn.close()

registered_devices = set()

# ==============================
# 데이터 모델
# ==============================
class LoginRequest(BaseModel):
    username: str
    password: str

class RegisterUserRequest(BaseModel):
    name: str
    phone_number: str
    id_number: str
    address: str
    gender: str
    emergency_contact: str
    blood_type: str
    known_diseases: str
    medications: str
    allergies: str
    height: float
    weight: float
    safety_training_completed: bool
    license_info: str
    profile_image_url: str
    device_id: str
    affiliation: str

class RegisterAdminRequest(BaseModel):
    name: str
    username: str
    password: str
    id_number: str
    profile_image_url: str
    affiliation: str

class RegisterDeviceRequest(BaseModel):
    device_id: str

class HeartRateRequest(BaseModel):
    device_id: str
    heart_rate: float

class AccelerationRequest(BaseModel):
    device_id: str
    acceleration: float

class LocationRequest(BaseModel):
    device_id: str
    latitude: float
    longitude: float

class StepCountRequest(BaseModel):
    device_id: str
    step_count: int

class HealthRecordRequest(BaseModel):
    device_id: str
    blood_pressure: str
    ecg_result: str
    spo2: float

class AlertRequest(BaseModel):
    device_id: str
    detected_abnormal: bool

# 임시 QR 토큰 저장소 (테스트용 메모리 저장 → 배포 시 Redis 추천)
qr_token_store = {}


# ==============================
# QR코드 생성성 API
# ==============================
@app.post("/api/generate_qr")
def generate_qr():
    try:
        # 1️⃣ 랜덤 UUID 토큰 생성
        token = str(uuid.uuid4())

        # 2️⃣ 유효시간 (예: 5분)
        expire_at = datetime.utcnow() + timedelta(minutes=5)

        # 3️⃣ 토큰 저장
        qr_token_store[token] = expire_at

        # 4️⃣ QR 코드 내용 (URL or 토큰만)
        qr_content = f"https://api.wearsafers.com/login?token={token}"

        # 5️⃣ QR 코드 이미지 생성
        qr_img = qrcode.make(qr_content)
        buffered = io.BytesIO()
        qr_img.save(buffered, format="PNG")
        img_str = base64.b64encode(buffered.getvalue()).decode()

        print(f"[DEBUG] QR 토큰 생성됨: {token}")

        # 6️⃣ Base64 인코딩된 이미지 반환
        return {
            "token": token,
            "expires_at": expire_at.isoformat(),
            "qr_image_base64": img_str
        }

    except Exception as e:
        print("[ERROR] QR 코드 생성 실패:", e)
        traceback.print_exc()
        raise HTTPException(status_code=500, detail="QR 코드 생성 실패")

# ==============================
# QR코드 유효성 검사사 API
# ==============================
@app.post("/api/validate_qr")
def validate_qr(token: str):
    expire_at = qr_token_store.get(token)
    
    if expire_at is None:
        # 저장된 토큰이 없으면 → 존재 안 함
        print("[DEBUG] QR token 없음")
        raise HTTPException(status_code=400, detail="QR token invalid")
    
    if datetime.utcnow() > expire_at:
        # 만료되었으면 → 삭제하고 만료 응답
        del qr_token_store[token]
        print("[DEBUG] QR token 만료됨 → 삭제")
        raise HTTPException(status_code=400, detail="QR token expired")
    
    # ✅ 유효하면 → 검증 성공 응답
    print("[DEBUG] QR token 유효함")
    return {"valid": True}


# ==============================
# 사용자 등록 API
# ==============================
@app.post("/api/register_user")
def register_user(data: RegisterUserRequest):
    print("[DEBUG] /api/register_user 호출됨")
    try:
        conn = get_db()
        cur = conn.cursor()
        cur.execute("""
            INSERT INTO users (name, phone_number, id_number, address, gender, emergency_contact,
                blood_type, known_diseases, medications, allergies, height, weight, safety_training_completed,
                license_info, profile_image_url, device_id, affiliation)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        """, (
            data.name, data.phone_number, data.id_number, data.address, data.gender,
            data.emergency_contact, data.blood_type, data.known_diseases, data.medications,
            data.allergies, data.height, data.weight, data.safety_training_completed,
            data.license_info, data.profile_image_url, data.device_id, data.affiliation
        ))
        conn.commit()
        print("[DEBUG] 사용자 등록 성공")
        return {"success": True}
    except Exception as e:
        print("[ERROR] 사용자 등록 실패:", e)
        traceback.print_exc()
        raise HTTPException(status_code=500, detail="등록 실패")
    finally:
        cur.close()
        conn.close()

# ==============================
# 관리자 등록 API
# ==============================
@app.post("/api/register_admin")
def register_admin(data: RegisterAdminRequest):
    print("[DEBUG] /api/register_admin 호출됨")
    conn = None
    cur = None
    try:
        conn = get_db()
        cur = conn.cursor()
        password_hash = bcrypt.hashpw(data.password.encode('utf-8'), bcrypt.gensalt()).decode('utf-8')
        cur.execute("""
            INSERT INTO admins (name, username, password_hash, id_number, profile_image_url, affiliation)
            VALUES (%s, %s, %s, %s, %s, %s)
        """, (
            data.name, data.username, password_hash, data.id_number, data.profile_image_url, data.affiliation
        ))
        conn.commit()
        print("[DEBUG] 관리자 등록 성공")
        return {"success": True}
    except Exception as e:
        print("[ERROR] 관리자 등록 실패:", e)
        traceback.print_exc()
        raise HTTPException(status_code=500, detail="등록 실패")
    finally:
        if cur:
            cur.close()
        if conn:
            conn.close()

# ==============================
# 사용자 로그인 API
# ==============================
@app.post("/api/login")
def login(data: LoginRequest):
    print("[DEBUG] /api/login 호출됨")
    try:
        conn = get_db()
        cur = conn.cursor()
        print(f"[DEBUG] 로그인 시도: username={data.username}")
        cur.execute("SELECT user_id FROM users WHERE phone_number = %s", (data.username,))
        user = cur.fetchone()
        if user:
            user_id = user[0]
            token_exp = datetime.utcnow() + timedelta(minutes=TOKEN_EXPIRATION_MINUTES)
            token = jwt.encode({"username": data.username, "exp": token_exp}, SECRET_KEY, algorithm="HS256")
            cur.execute("INSERT INTO login_logs (user_id, login_time) VALUES (%s, CURRENT_TIMESTAMP)", (user_id,))
            cur.execute("INSERT INTO auth_tokens (user_id, token, created_at, expires_at) VALUES (%s, %s, CURRENT_TIMESTAMP, %s)",
                        (user_id, token, token_exp))
            conn.commit()
            print("[DEBUG] 로그인 성공, 토큰 발급됨")
            return {"token": token}
        else:
            print("[DEBUG] 로그인 실패: 사용자 없음")
            raise HTTPException(status_code=401, detail="Invalid credentials")
    except Exception as e:
        print("[ERROR] 로그인 에러:", e)
        traceback.print_exc()
        raise HTTPException(status_code=500, detail="Login error")
    finally:
        cur.close()
        conn.close()

# ==============================
# 관리자 로그인 API
# ==============================
@app.post("/api/login_admin")
def login_admin(data: LoginRequest):
    print("[DEBUG] /api/login_admin 호출됨")
    try:
        conn = get_db()
        cur = conn.cursor()
        print(f"[DEBUG] 관리자 로그인 시도: username={data.username}")
        cur.execute("SELECT admin_id, password_hash FROM admins WHERE username = %s", (data.username,))
        admin = cur.fetchone()
        if admin:
            admin_id, password_hash = admin
            if bcrypt.checkpw(data.password.encode('utf-8'), password_hash.encode('utf-8')):
                token_exp = datetime.utcnow() + timedelta(minutes=TOKEN_EXPIRATION_MINUTES)
                token = jwt.encode({"admin": data.username, "exp": token_exp}, SECRET_KEY, algorithm="HS256")
                cur.execute("INSERT INTO login_logs (admin_id, login_time) VALUES (%s, CURRENT_TIMESTAMP)", (admin_id,))
                cur.execute("""
                    INSERT INTO auth_tokens (admin_id, token, created_at, expires_at)
                    VALUES (%s, %s, CURRENT_TIMESTAMP, %s)
                """, (admin_id, token, token_exp))
                conn.commit()
                print("[DEBUG] 관리자 로그인 성공, 토큰 발급됨")
                return {"token": token}
            else:
                print("[DEBUG] 관리자 로그인 실패: 비밀번호 불일치")
                raise HTTPException(status_code=401, detail="Invalid credentials")
        else:
            print("[DEBUG] 관리자 로그인 실패: 계정 없음")
            raise HTTPException(status_code=401, detail="Invalid credentials")
    except Exception as e:
        print("[ERROR] 관리자 로그인 에러:", e)
        traceback.print_exc()
        raise HTTPException(status_code=500, detail="Login error")
    finally:
        cur.close()
        conn.close()

# ==============================
# 디바이스 등록 API
# ==============================
@app.post("/api/register_device")
def register_device(data: RegisterDeviceRequest, token: str = Depends(oauth2_scheme)):
    print("[DEBUG] /api/register_device 호출됨")
    try:
        payload = jwt.decode(token, SECRET_KEY, algorithms=["HS256"])
        registered_devices.add(data.device_id)
        print(f"[DEBUG] 디바이스 등록됨: {data.device_id}")
        return {"success": True}
    except jwt.ExpiredSignatureError:
        print("[ERROR] Token expired")
        raise HTTPException(status_code=401, detail="Token expired")
    except jwt.InvalidTokenError:
        print("[ERROR] Invalid token")
        raise HTTPException(status_code=401, detail="Invalid token")

# ==============================
# 사용자 정보 요청 API
# ==============================
@app.get("/api/users_status")
def get_users_status():
    print("[DEBUG] /api/users_status 호출됨")
    conn = get_db()
    cur = conn.cursor()
    try:
        cur.execute("""
            SELECT
                u.name,
                u.affiliation,
                u.phone_number,
                COALESCE(r.heart_rate || ' bpm', 'N/A') AS heart_rate,
                COALESCE(EXTRACT(EPOCH FROM (NOW() - r.timestamp))/60, 0) || ' min' AS inactivity_time,
                COALESCE(r.steps, 0) AS steps_per_minute
            FROM users u
            LEFT JOIN realtime_data r ON u.user_id = r.user_id
            GROUP BY u.user_id, r.heart_rate, r.timestamp, r.steps;
        """)
        rows = cur.fetchall()
        columns = [desc[0] for desc in cur.description]
        result = [dict(zip(columns, row)) for row in rows]
        return result
    except Exception as e:
        print("[ERROR] get_users_status 실패:", e)
        traceback.print_exc()
        raise HTTPException(status_code=500, detail="DB query failed")
    finally:
        cur.close()
        conn.close()


# ==============================
# QR코드 로그인 API
# ==============================

@app.post("/api/qr_login")
def qr_login(data: LoginRequest):
    try:
        conn = get_db()
        cur = conn.cursor()
        cur.execute("SELECT user_id FROM users WHERE phone_number = %s", (data.username,))
        user = cur.fetchone()
        if user:
            user_id = user[0]
            token_exp = datetime.utcnow() + timedelta(minutes=TOKEN_EXPIRATION_MINUTES)
            token = jwt.encode({"username": data.username, "exp": token_exp}, SECRET_KEY, algorithm="HS256")
            cur.execute("INSERT INTO login_logs (user_id, login_time) VALUES (%s, CURRENT_TIMESTAMP)", (user_id,))
            cur.execute("INSERT INTO auth_tokens (user_id, token, created_at, expires_at) VALUES (%s, %s, CURRENT_TIMESTAMP, %s)",
                        (user_id, token, token_exp))
            conn.commit()
            return {"token": token}
        else:
            raise HTTPException(status_code=401, detail="번호에 해당하는 사용자 없음")
    except Exception as e:
        traceback.print_exc()
        raise HTTPException(status_code=500, detail="서버 오류")
    finally:
        cur.close()
        conn.close()


# ==============================
# 실시간 데이터 저장 API
# ==============================
def insert_realtime_data(device_id, heart_rate=None, acceleration=None, steps=None, latitude=None, longitude=None):
    print(f"[DEBUG] insert_realtime_data 호출됨: device_id={device_id}")
    conn = get_db()
    cur = conn.cursor()
    try:
        cur.execute("SELECT user_id FROM users WHERE device_id = %s", (device_id,))
        user = cur.fetchone()
        if user:
            user_id = user[0]
            cur.execute("""
                INSERT INTO realtime_data (user_id, heart_rate, acceleration, steps, latitude, longitude)
                VALUES (%s, %s, %s, %s, %s, %s)
            """, (user_id, heart_rate, acceleration, steps, latitude, longitude))
            conn.commit()
            print("[DEBUG] 실시간 데이터 저장 성공")
        else:
            print("[DEBUG] 해당 디바이스의 user_id 찾지 못함")
    except Exception as e:
        print("[ERROR] insert_realtime_data 실패:", e)
        traceback.print_exc()
    finally:
        cur.close()
        conn.close()

@app.post("/api/heartrate")
def receive_heartrate(data: HeartRateRequest, token: str = Depends(oauth2_scheme)):
    print("[DEBUG] /api/heartrate 호출됨")
    validate_device(token, data.device_id)
    insert_realtime_data(data.device_id, heart_rate=data.heart_rate)
    return {"success": True}

@app.post("/api/acceleration")
def receive_acceleration(data: AccelerationRequest, token: str = Depends(oauth2_scheme)):
    print("[DEBUG] /api/acceleration 호출됨")
    validate_device(token, data.device_id)
    insert_realtime_data(data.device_id, acceleration=data.acceleration)
    return {"success": True}

@app.post("/api/location")
def receive_location(data: LocationRequest, token: str = Depends(oauth2_scheme)):
    print("[DEBUG] /api/location 호출됨")
    validate_device(token, data.device_id)
    insert_realtime_data(data.device_id, latitude=data.latitude, longitude=data.longitude)
    return {"success": True}
    
@app.post("/api/request_realtime_gps")
def request_realtime_gps(device_id: str):
    print(f"[DEBUG] /api/request_realtime_gps 호출됨: device_id={device_id}")
    realtime_gps_requests.add(device_id)
    print(f"[DEBUG] device_id {device_id}에 대해 실시간 GPS 요청됨")
    return {"success": True}

@app.get("/api/check_realtime_gps")
def check_realtime_gps(device_id: str):
    if device_id in realtime_gps_requests:
        realtime_gps_requests.remove(device_id)  # 요청 확인되면 플래그 제거
        return {"realtime_gps_needed": True}
    return {"realtime_gps_needed": False}

@app.post("/api/stepcount")
def receive_stepcount(data: StepCountRequest, token: str = Depends(oauth2_scheme)):
    print("[DEBUG] /api/stepcount 호출됨")
    validate_device(token, data.device_id)
    insert_realtime_data(data.device_id, steps=data.step_count)
    return {"success": True}

@app.post("/api/health_record")
def save_health_record(data: HealthRecordRequest, token: str = Depends(oauth2_scheme)):
    print("[DEBUG] /api/health_record 호출됨")
    validate_device(token, data.device_id)
    conn = get_db()
    cur = conn.cursor()
    try:
        cur.execute("SELECT user_id FROM users WHERE device_id = %s", (data.device_id,))
        user = cur.fetchone()
        if user:
            user_id = user[0]
            cur.execute("""
                INSERT INTO health_records (user_id, blood_pressure, ecg_result, spo2)
                VALUES (%s, %s, %s, %s)
            """, (user_id, data.blood_pressure, data.ecg_result, data.spo2))
            conn.commit()
            print("[DEBUG] 건강 기록 저장 성공")
        else:
            print("[DEBUG] 해당 디바이스의 user_id 찾지 못함")
    except Exception as e:
        print("[ERROR] health_record 저장 실패:", e)
        traceback.print_exc()
    finally:
        cur.close()
        conn.close()
    return {"success": True}

@app.post("/api/alert")
def create_alert(data: AlertRequest, token: str = Depends(oauth2_scheme)):
    print("[DEBUG] /api/alert 호출됨")
    validate_device(token, data.device_id)
    conn = get_db()
    cur = conn.cursor()
    try:
        cur.execute("SELECT user_id FROM users WHERE device_id = %s", (data.device_id,))
        user = cur.fetchone()
        if user:
            user_id = user[0]
            cur.execute("""
                INSERT INTO alerts (user_id, detected_abnormal)
                VALUES (%s, %s)
            """, (user_id, data.detected_abnormal))
            conn.commit()
            print("[DEBUG] 알람 저장 성공")
        else:
            print("[DEBUG] 해당 디바이스의 user_id 찾지 못함")
    except Exception as e:
        print("[ERROR] alert 저장 실패:", e)
        traceback.print_exc()
    finally:
        cur.close()
        conn.close()
    return {"success": True}

def validate_device(token, device_id):
    print(f"[DEBUG] validate_device 호출됨: device_id={device_id}")
    try:
        payload = jwt.decode(token, SECRET_KEY, algorithms=["HS256"])
        if device_id not in registered_devices:
            print("[ERROR] Unauthorized device")
            raise HTTPException(status_code=403, detail="Unauthorized device")
    except jwt.InvalidTokenError:
        print("[ERROR] Invalid token")
        raise HTTPException(status_code=401, detail="Invalid token")

if __name__ == "__main__":
    print("[DEBUG] __main__ 실행, create_tables 호출")
    create_tables()
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)

