package com.opentuter.profileservice.dto;

import java.util.List;

public class ProfileUpdateDto {


    public String email;
    public String role;

    public String fullName;
    public String bio;
    public String imageUrl;
    public List<String> socialMediaUrls;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
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
