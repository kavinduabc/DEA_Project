package com.opentuter.profileservice.dto;

import com.opentuter.profileservice.util.Utils;
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
public class ProfileRequestDto {

    @Email(message = Utils.EMAIL_VALIDE)
    @NotBlank(message = Utils.EMAIL_REQUIRED)
    public String email;

    @NotBlank(message = Utils.PASSWORD_REQUIRED)
    public String password;

    @NotBlank(message = Utils.ROLE_REQUIRED)
    public String role;

    @NotBlank(message = Utils.NAME_REQUIRED)
    public String fullName;

    public String bio;

    public String imageUrl;

    public List<String> socialMediaUrls;


    public @Email(message = Utils.EMAIL_VALIDE) @NotBlank(message = Utils.EMAIL_REQUIRED) String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = Utils.EMAIL_VALIDE) @NotBlank(message = Utils.EMAIL_REQUIRED) String email) {
        this.email = email;
    }

    public @NotBlank(message = Utils.PASSWORD_REQUIRED) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = Utils.PASSWORD_REQUIRED) String password) {
        this.password = password;
    }

    public @NotBlank(message = Utils.ROLE_REQUIRED) String getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = Utils.ROLE_REQUIRED) String role) {
        this.role = role;
    }

    public @NotBlank(message = Utils.NAME_REQUIRED) String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank(message = Utils.NAME_REQUIRED) String fullName) {
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
