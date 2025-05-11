package com.project.user.entity;

import jakarta.persistence.*;

/**
 * packageName    : com.project.user.entity
 * fileName       : User
 * author         : Administrator
 * date           : 2025-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-05-10        Administrator       최초 생성
 */

@Entity
@Table(name = "`T_USER`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    // 기본 생성자, getter, setter, toString() 등 생략

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}