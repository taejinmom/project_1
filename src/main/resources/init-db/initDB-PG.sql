-- 사용자 마스터 테이블 생성
CREATE TABLE IF NOT EXISTS t_user (
    user_id VARCHAR(40) PRIMARY KEY,
    login_id VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    address TEXT,
    user_role VARCHAR(20) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 카테고리 테이블
CREATE TABLE IF NOT EXISTS t_category (
    category_id VARCHAR(40) PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL,
    parent_id VARCHAR(36),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 상품 테이블
CREATE TABLE IF NOT EXISTS t_product (
    product_id VARCHAR(40) PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    description TEXT,
    price INT NOT NULL,
    stock_quantity INT DEFAULT 0,
    category_id VARCHAR(36),
    thumbnail_url TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 상품 이미지 테이블
CREATE TABLE IF NOT EXISTS t_product_image (
    image_id VARCHAR(40) PRIMARY KEY,
    product_id VARCHAR(40),
    image_url TEXT NOT NULL,
    is_thumbnail BOOLEAN DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

delete from t_user;
delete from t_category;
delete from t_product;
delete from t_product_image;
-- 데이터 삽입
-- 관리자 계정 1개
--INSERT INTO t_user (user_id, login_id, password, name, email, phone, address, user_role)
--VALUES ('550e8400-e29b-41d4-a716-446655440000', 'admin', 'a', '관리자', 'admin@example.com', '010-1111-1111', '서울시 종로구', 'ADMIN');
--
---- 일반 사용자 4개
--INSERT INTO t_user (user_id, login_id, password, name, email, phone, address, user_role)
--VALUES ('550e8400-e29b-41d4-a716-446655440001', 'user1', 'a', '홍길동', 'user1@example.com', '010-2222-2222', '서울시 강남구', 'USER');
--
--INSERT INTO t_user (user_id, login_id, password, name, email, phone, address, user_role)
--VALUES ('550e8400-e29b-41d4-a716-446655440002', 'user2', 'a', '김영희', 'user2@example.com', '010-3333-3333', '서울시 마포구', 'USER');
--
--INSERT INTO t_user (user_id, login_id, password, name, email, phone, address, user_role)
--VALUES ('550e8400-e29b-41d4-a716-446655440003', 'user3', 'a', '박철수', 'user3@example.com', '010-4444-4444', '서울시 송파구', 'USER');
--
--INSERT INTO t_user (user_id, login_id, password, name, email, phone, address, user_role)
--VALUES ('550e8400-e29b-41d4-a716-446655440004', 'user4', 'a', '이민정', 'user4@example.com', '010-5555-5555', '서울시 서초구', 'USER');

