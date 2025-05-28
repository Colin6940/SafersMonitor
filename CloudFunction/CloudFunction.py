import functions_framework
from google.cloud import firestore
import psycopg2

db = firestore.Client()

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

    # PostgreSQL 연동(실서비스용 DB 접속 정보 필요)
    conn = psycopg2.connect(...)
    cur = conn.cursor()
    cur.execute("SELECT user_id FROM users WHERE phone_number = %s", (phone_number,))
    user = cur.fetchone()

    if user:
        # 성공: status, 토큰 update
        db.collection("qr_login_requests").document(doc_id).update({
            "status": "success",
            "token": "여기에발급된JWT"  # 필요 시 토큰 발급/전달
        })
    else:
        # 실패
        db.collection("qr_login_requests").document(doc_id).update({
            "status": "fail"
        })

    cur.close()
    conn.close()
