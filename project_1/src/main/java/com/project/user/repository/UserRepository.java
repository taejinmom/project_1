package com.project.user.repository;

import com.project.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.project.user.repository
 * fileName       : UserRepository
 * author         : Administrator
 * date           : 2025-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-05-10        Administrator       최초 생성
 */
public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자 이름으로 사용자 조회 (쿼리 메소드 방식)
    User findByUsername(String username);
}
