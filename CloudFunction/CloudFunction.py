import functions_framework
from google.cloud import firestore
import psycopg2
import jwt
import os
from datetime import datetime, timedelta

db = firestore.Client()

# 환경변수에서 비밀키, DB 정보 읽기
JWT_SECRET_KEY = os.environ.get("JWT_SECRET_KEY", "your-very-secret-key")
JWT_ALGORITHM = "HS256"
JWT_EXP_MINUTES = int(os.environ.get("JWT_EXP_MINUTES", "60"))

DB_NAME = os.environ.get("DB_NAME", "your_db_name")
DB_USER = os.environ.get("DB_USER", "your_db_user")
DB_PASSWORD = os.environ.get("DB_PASSWORD", "your_db_password")
DB_HOST = os.environ.get("DB_HOST", "localhost")
DB_PORT = os.environ.get("DB_PORT", "5432")

@functions_framework.cloud_event
def qr_login_firestore_trigger(cloud_event):
    # Firestore 트리거 이벤트 구조 참조
    value = cloud_event.data["value"]
    fields = value.get("fields", {})
    status = fields.get("status", {}).get("stringValue", "")

    if status != "pending":
        return

    phone_number = fields.get("phone_number", {}).get("stringValue", "")
    doc_id = value["name"].split("/")[-1]

    # PostgreSQL 연동 (환경변수 기반)
    conn = psycopg2.connect(
        dbname=DB_NAME,
        user=DB_USER,
        password=DB_PASSWORD,
        host=DB_HOST,
        port=DB_PORT,
    )
    cur = conn.cursor()
    cur.execute("SELECT user_id FROM users WHERE phone_number = %s", (phone_number,))
    user = cur.fetchone()

    if user:
        user_id = user[0]
        now = datetime.utcnow()
        exp = now + timedelta(minutes=JWT_EXP_MINUTES)
        payload = {
            "user_id": user_id,
            "phone_number": phone_number,
            "exp": exp
        }
        token = jwt.encode(payload, JWT_SECRET_KEY, algorithm=JWT_ALGORITHM)

        db.collection("qr_login_requests").document(doc_id).update({
            "status": "success",
            "token": token
        })
    else:
        db.collection("qr_login_requests").document(doc_id).update({
            "status": "fail"
        })

    cur.close()
    conn.close()
