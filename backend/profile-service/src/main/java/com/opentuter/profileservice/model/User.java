package com.opentuter.profileservice.model;


import com.opentuter.profileservice.util.Utils;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

//**
// import jakarta and jakarta.validation -> for validation and persistence
// import java.util -> List and UUID
// */

//**
// This is user model.It consists of
//    id (UUID)
//    email, password,role, (String)
//    localDatetime (LocalDateTime)
//    and Profile
//    uuid is a primary key
//    use anotations :@Entity ,@TAble ,@ID,@Email
//    */
@Entity
@Table(name = Utils.USER_TABLE)
public class User {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = Utils.ID, updatable = false, nullable = false)
    private UUID id;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private  String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    private String role;

    //**
    // @OneToOne -> one user has one profile , one profile belongs to one user
    // mappedBy = "user" ->  refers to the variable name inside profile class
    // cascade = CascadeType.ALL -> save, update , delete all of are include in this
    //       this method use to , if do the operation on user, it will also apply to profile
    //       accordingly , profile also saved automatically
    // orphanRemoval = true -> this method allows  to , if a remove profile
    //   from user , profile record  will be automatically delete from database automatically.
    // fetch = FetchType.LAZY ->   */
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Profile profile;

    private LocalDateTime createAt = LocalDateTime.now();

    public User() {
        // Default constructor for JPA
    }

    public User(UUID id, String email, String password, String role, Profile profile, LocalDateTime createAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.profile = profile;
        this.createAt = createAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
