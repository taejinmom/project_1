package com.project.config.initDB.data.user;

import com.project.user.entity.User;
import com.project.user.entity.UserRole;
import com.project.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * packageName    : com.project.h2
 * fileName       : UserDataGenerator
 * author         : Administrator
 * date           : 2025-05-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-05-13        Administrator       최초 생성
 */

@Service
@RequiredArgsConstructor
public class UserDataGenerator {

    private final UserRepository userRepository;
    @PostConstruct
    public void generatorUser() {
        List<User> users = List.of(
                createUser("550e8400-e29b-41d4-a716-446655440000", "admin", "a", "관리자", "admin@example.com", "010-1111-1111", "서울시 종로구", UserRole.ADMIN),
                createUser("550e8400-e29b-41d4-a716-446655440001", "user1", "a", "홍길동", "user1@example.com", "010-2222-2222", "서울시 강남구", UserRole.USER),
                createUser("550e8400-e29b-41d4-a716-446655440002", "user2", "a", "김영희", "user2@example.com", "010-3333-3333", "서울시 마포구", UserRole.USER),
                createUser("550e8400-e29b-41d4-a716-446655440003", "user3", "a", "박철수", "user3@example.com", "010-4444-4444", "서울시 송파구", UserRole.USER),
                createUser("550e8400-e29b-41d4-a716-446655440004", "user4", "a", "이민정", "user4@example.com", "010-5555-5555", "서울시 서초구", UserRole.USER)
        );

        userRepository.saveAll(users);
    }
    private User createUser(String id, String loginId, String password, String name, String email, String phone, String address, UserRole role) {
        User user = new User();
        user.setUserId(UUID.fromString(id).toString());
        user.setLoginId(loginId);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(role);
        return user;
    }
}
