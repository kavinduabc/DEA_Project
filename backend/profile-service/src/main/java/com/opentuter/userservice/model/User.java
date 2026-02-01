package com.opentuter.userservice.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity

public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Email(message = "Email should be valid")
@NotBlank(message = "Email is required")
@Column(nullable = false , unique = true)
private String email;

@NotBlank(message = "Password is required")
@Column(nullable = false, unique = true)
private String password;

@NotBlank(message = "Role is required")
    private String role;

private LocalDateTime createdAt;

    public User(Long id, String email, String role, String password ,LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Email(message = "Email should be valid") @NotBlank(message = "Email is required") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email should be valid") @NotBlank(message = "Email is required") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Role is required") String getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Role is required") String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
