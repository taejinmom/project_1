package com.project.user.service;

import com.project.user.entity.User;
import com.project.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : com.project.user.service
 * fileName       : UserService
 * author         : Administrator
 * date           : 2025-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-05-10        Administrator       최초 생성
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 사용자 이름으로 사용자 찾기
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 사용자 전체 리스트 가져오기
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
