package com.ignius.spring_security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String role;
    private String status;
}
