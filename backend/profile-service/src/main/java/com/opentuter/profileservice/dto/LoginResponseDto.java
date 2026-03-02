package com.opentuter.profileservice.dto;

import java.time.Instant;

public class LoginResponseDto {

    private String token;
    private String email;
    private String role;
    private Instant expiresAt;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String token, String email, String role, Instant expiresAt) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}

