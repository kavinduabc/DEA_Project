package com.opentuter.userservice.dto;


import java.util.List;

public class ProfileDto {

    private String fullName;
    private String bio;
    private String imageUrl;
    private List<String> socailMediaUrl;

    public ProfileDto() {}

    public ProfileDto(String fullName, String bio, String imageUrl, List<String> socailMediaUrl) {
        this.fullName = fullName;
        this.bio = bio;
        this.imageUrl = imageUrl;
        this.socailMediaUrl = socailMediaUrl;
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

    public List<String> getSocailMediaUrl() {
        return socailMediaUrl;
    }

    public void setSocailMediaUrl(List<String> socailMediaUrl) {
        this.socailMediaUrl = socailMediaUrl;
    }
}
