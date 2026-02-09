package com.opentuter.profileservice.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID uuid;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private  String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    private String role;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Profile profile;

    private LocalDateTime createAt;

    public User(UUID uuid, String email, String password, String role, Profile profile, LocalDateTime createAt) {
        this.uuid = uuid;
        this.email = email;
        this.password = password;
        this.role = role;
        this.profile = profile;
        this.createAt = createAt;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public @Email @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank String email) {
        this.email = email;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public @NotBlank String getRole() {
        return role;
    }

    public void setRole(@NotBlank String role) {
        this.role = role;
    }

    public Profile getProfile() {
        return profile;
    }

//    public void setProfile(Profile profile) {
//        this.profile = profile;
//    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
        if(profile != null)
        {
            profile.setUser(this);
        }
    }
}
