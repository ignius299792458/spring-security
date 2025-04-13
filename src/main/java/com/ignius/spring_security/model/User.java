package com.ignius.spring_security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Table(name="users")
@AllArgsConstructor
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    @CreatedDate
    private Instant createdAt;
}
