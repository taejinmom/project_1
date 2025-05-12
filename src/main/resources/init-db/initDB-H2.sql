--
---- 사용자 마스터 테이블 생성
--CREATE TABLE IF NOT EXISTS "T_USER" (
--    "userId" VARCHAR(40) PRIMARY KEY,
--    "username" VARCHAR(50) NOT NULL UNIQUE,
--    "password" VARCHAR(255) NOT NULL,
--    "name" VARCHAR(100) NOT NULL,
--    "email" VARCHAR(100) NOT NULL UNIQUE,
--    "phone" VARCHAR(20),
--    "address" TEXT,
--    "userRole" VARCHAR(20) DEFAULT 'USER',
--    "createdAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--    "updatedAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP
--);
--
---- 카테고리 테이블
--CREATE TABLE IF NOT EXISTS "T_CATEGORY" (
--    "categoryId" VARCHAR(40) PRIMARY KEY,
--    "categoryName" VARCHAR(100) NOT NULL,
--    "parentId" VARCHAR(36),
--    "createdAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--    "updatedAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP
--);
--
---- 상품 테이블
--CREATE TABLE IF NOT EXISTS "T_PRODUCT" (
--    "productId" VARCHAR(40) PRIMARY KEY,
--    "productName" VARCHAR(255) NOT NULL,
--    "description" TEXT,
--    "price" INT NOT NULL,
--    "stockQuantity" INT DEFAULT 0,
--    "categoryId" VARCHAR(36),
--    "thumbnailUrl" TEXT,
--    "createdAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--    "updatedAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--
--    CONSTRAINT "fk_product_category"
--        FOREIGN KEY ("categoryId")
--        REFERENCES "T_CATEGORY"("categoryId")
--        ON DELETE SET NULL
--);
--
---- 상품 이미지 테이블
--CREATE TABLE IF NOT EXISTS "T_PRODUCT_IMAGE" (
--    "imageId" VARCHAR(40) PRIMARY KEY,
--    "productId" VARCHAR(40),
--    "imageUrl" TEXT NOT NULL,
--    "isThumbnail" BOOLEAN DEFAULT false,
--    "createdAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--
--    CONSTRAINT "fk_product_image_product"
--        FOREIGN KEY ("productId")
--        REFERENCES "T_PRODUCT"("productId")
--        ON DELETE CASCADE
--);

-- 데이터 삽입
-- 관리자 계정 1개
INSERT INTO T_USER (user_id, login_id, password, name, email, phone, address, user_role)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'admin', 'a', '관리자', 'admin@example.com', '010-1111-1111', '서울시 종로구', 'ADMIN');

-- 일반 사용자 4개
INSERT INTO T_USER (user_id, login_id, password, name, email, phone, address, user_role)
VALUES ('550e8400-e29b-41d4-a716-446655440001', 'user1', 'a', '홍길동', 'user1@example.com', '010-2222-2222', '서울시 강남구', 'USER');

INSERT INTO T_USER (user_id, login_id, password, name, email, phone, address, user_role)
VALUES ('550e8400-e29b-41d4-a716-446655440002', 'user2', 'a', '김영희', 'user2@example.com', '010-3333-3333', '서울시 마포구', 'USER');

INSERT INTO T_USER (user_id, login_id, password, name, email, phone, address, user_role)
VALUES ('550e8400-e29b-41d4-a716-446655440003', 'user3', 'a', '박철수', 'user3@example.com', '010-4444-4444', '서울시 송파구', 'USER');

INSERT INTO T_USER (user_id, login_id, password, name, email, phone, address, user_role)
VALUES ('550e8400-e29b-41d4-a716-446655440004', 'user4', 'a', '이민정', 'user4@example.com', '010-5555-5555', '서울시 서초구', 'USER');
