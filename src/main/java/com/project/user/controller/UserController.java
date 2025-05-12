package com.project.user.controller;

import com.project.user.entity.User;
import com.project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        try {
            User user = userService.findById(username).orElse(new User());
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);  // 사용자 발견시 200 OK와 함께 반환
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 사용자 없으면 404 Not Found 반환
            }
        } catch (Exception e) {
            logger.error("Error fetching user with username: " + username, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // 500 오류 반환
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            if (users != null && !users.isEmpty()) {
                return new ResponseEntity<>(users, HttpStatus.OK);  // 사용자 목록이 있으면 200 OK와 함께 반환
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 사용자 목록이 없으면 204 No Content 반환
            }
        } catch (Exception e) {
            logger.error("Error fetching all users", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // 500 오류 반환
        }
    }
}
