package com.opentuter.profileservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


//**
// A DTO is like a  saaled envelope.
// it is use for carry a data detween frontend to backend
// */
//**
// A DTO is a plain class used to send data
// between backend and frontend.
// --Important in DTO class--
//   security purpose .don't accidentally leak sensitive data
//   you send smaller amount od data over the internet, which makes your app faster
//   */
public class UserProfileRequestDto {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    public String email;

    @NotBlank(message = "Password is required")
    public String password;

    @NotBlank(message = "Role is required")
    public String role;

    @NotBlank(message = "Name is required")
    public String fullName;

    public String bio;

    public String imageUrl;

    public List<String> socialMediaUrls;


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

    public @NotBlank(message = "Name is required") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank(message = "Name is required") String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getSocialMediaUrls() {
        return socialMediaUrls;
    }

    public void setSocialMediaUrls(List<String> socialMediaUrls) {
        this.socialMediaUrls = socialMediaUrls;
    }
}
