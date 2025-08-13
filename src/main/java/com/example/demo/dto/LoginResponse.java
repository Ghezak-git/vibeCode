package com.example.demo.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Long expiresIn;
    private UserProfile user;
}
