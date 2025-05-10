-- 사용자 생성 (H2에서는 IF NOT EXISTS를 지원하지 않으므로, 사용자 생성 전에 먼저 확인 필요)
-- H2에서는 기본적으로 관리자가 아닌 사용자가 CREATE USER를 실행할 수 없으므로, DB 관리자가 실행해야 합니다.
CREATE USER IF NOT EXISTS PROJECT_1 PASSWORD 'PROJECT_1_DBA';

-- PUBLIC 스키마에 대한 권한 부여 (H2에서 PUBLIC 스키마 권한 부여 가능)
GRANT ALL ON SCHEMA PUBLIC TO PROJECT_1;

-- T_USER 테이블 생성
CREATE TABLE IF NOT EXISTS T_USER (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    USERNAME VARCHAR(50),
    PASSWORD VARCHAR(100)
);

-- 데이터 삽입
INSERT INTO "t_user" (USERNAME, PASSWORD) VALUES ('admin', 'a');
