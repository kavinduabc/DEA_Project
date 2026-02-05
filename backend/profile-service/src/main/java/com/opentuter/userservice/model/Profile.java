package com.opentuter.userservice.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    private Long id;
    private String fullName;
    private String bio;
    private String imageUrl;
    private List<String> socialMediaUrl;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getSocialMediaUrl() {
        return socialMediaUrl;
    }

    public void setSocialMediaUrl(List<String> socialMediaUrl) {
        this.socialMediaUrl = socialMediaUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
