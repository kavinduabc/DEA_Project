package com.opentuter.profileservice.model;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "profiles")
public class Profile {

   @Id
   private UUID uuid;
   private String fullName;
   private String bio;
   private String imageUrl;


    private List<String> socialMediaUrls;

   @OneToOne
    @MapsId
    @JoinColumn(name = "uuid")
    private User user;

    public Profile(UUID uuid, String fullName, String bio, String imageUrl, List<String> socialMediaUrls, User user) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.bio = bio;
        this.imageUrl = imageUrl;
        this.socialMediaUrls = socialMediaUrls;
        this.user = user;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
