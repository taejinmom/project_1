-- H2에서는 사용자 생성 구문을 생략해야 합니다.
-- H2는 CREATE USER를 지원하지 않으며, 사용자 생성 및 권한 부여는 데이터베이스 관리자가 별도로 수행해야 합니다.

-- T_USER 테이블 생성
CREATE TABLE IF NOT EXISTS T_USER (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    USERNAME VARCHAR(50),
    PASSWORD VARCHAR(100)
);

-- 데이터 삽입
-- H2에서는 'ON CONFLICT' 구문을 사용할 수 없으므로, 이를 'ON DUPLICATE KEY UPDATE'로 변경해야 합니다.
-- 이미 존재하는 사용자 이름에 대해서는 비밀번호를 업데이트합니다.
INSERT INTO 't_user' (USERNAME, PASSWORD) VALUES ('admin', 'a');

