DB폴더.
PostgreSQL
FastAPI backendAPI




PostgreSQL서버 관리 명령어 리스트


1. PostgreSQL 서버 실행/중지/상태 확인

🖥 Linux / MacOS (systemctl 사용):
bash
 
sudo systemctl start postgresql     # 서버 시작
sudo systemctl stop postgresql      # 서버 중지
sudo systemctl restart postgresql   # 서버 재시작
sudo systemctl status postgresql    # 상태 확인

🖥 Windows (서비스 명령어):
Windows 검색창 → 서비스 → PostgreSQL 서버 찾아서 수동 실행/중지

또는 cmd에:

cmd
 
net start postgresql-x64-16   # 버전에 따라 다름
net stop postgresql-x64-16
(16 → PostgreSQL 버전에 맞게 수정 필요. DB작업에는 16 사용함.)





2. psql 접속 (PostgreSQL 콘솔 접속)

bash
psql -U postgres
(→ postgres 사용자 계정으로 접속. 비밀번호 입력 필요)

특정 DB로 바로 접속:
bash
psql -U postgres –d wearsafers_db




3. 데이터베이스 관련 명령어 (psql 내부 명령어)

psql에 접속하면 아래 명령어 사용:

명령어			설명
\l		전체 데이터베이스 목록
\c dbname	특정 데이터베이스 접속
\dt		현재 DB의 테이블 목록
\d tablename	테이블 상세 구조 확인
\du		사용자 목록
\q		psql 종료




4. SQL 기본 명령어

SQL 문법	설명
SELECT * FROM users;					users 테이블 전체 조회
INSERT INTO users (...) VALUES (...);			데이터 삽입
UPDATE users SET name='홍길동' WHERE user_id=1;		데이터 수정
DELETE FROM users WHERE user_id=1;			데이터 삭제
DROP TABLE users;					테이블 삭제





5. 사용자 및 권한 관리

사용자 생성:
sql
 CREATE USER newuser WITH PASSWORD '비밀번호';

사용자에게 DB 권한 부여:
sql 
GRANT ALL PRIVILEGES ON DATABASE wearsafers_db TO newuser;

특정 테이블만 권한 부여:
sql
GRANT SELECT, INSERT ON users TO newuser;






6. 백업 및 복구 (CLI)

DB 백업:
bash
pg_dump -U postgres wearsafers_db > backup.sql

DB 복구:
bash
 psql -U postgres -d wearsafers_db -f backup.sql



7. PostgreSQL 서버 재설정 및 설정 확인

설정 파일 위치 확인:
sql
SHOW config_file;

postgresql.conf, pg_hba.conf 수정 후 → 재시작 필요
bash
sudo systemctl restart postgresql



8. 계정 비밀번호 변경
sql
ALTER USER postgres WITH PASSWORD '새비밀번호';



9. 테이블 내 데이터 개수 확인
sql
SELECT COUNT(*) FROM users;



10. index 확인 및 생성
sql
\di   -- 인덱스 리스트
CREATE INDEX idx_users_phone ON users(phone_number);



✅ 주의사항

- 실시간 서비스 서버에서 DROP / TRUNCATE 신중히

- 백업 주기적 실행 (pg_dump 필수)

- DB 계정 권한 최소화