package com.opentuter.userservice.model;


import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    private Long id;
    private String fullName;
    private String bio;
    private String imageUrl;
    private String scialMediaUrl;

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

    public String getScialMediaUrl() {
        return scialMediaUrl;
    }

    public void setScialMediaUrl(String scialMediaUrl) {
        this.scialMediaUrl = scialMediaUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
