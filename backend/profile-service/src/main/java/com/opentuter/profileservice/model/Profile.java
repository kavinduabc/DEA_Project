package com.opentuter.profileservice.model;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
//**
// import jakarta
// import java.util -> List and UUID*/


//**
// This is profile  model.It consists of
//    id (UUID)
//    fullName, bio, imageUrls and socialMediaUrl (String)
//    and user (User) as a one-to-one relationship
//    uuid is a foriegn key key
//    use anotations :@Entity ,@TAble ,@ID,@Email
//*/


@Entity
@Table(name = "profiles")
public class Profile {

   @Id
   private UUID id;
   private String fullName;
   private String bio;
   private String imageUrl;


    private List<String> socialMediaUrls;

   @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    public Profile(UUID id, String fullName, String bio, String imageUrl, List<String> socialMediaUrls, User user) {
        this.id = id;
        this.fullName = fullName;
        this.bio = bio;
        this.imageUrl = imageUrl;
        this.socialMediaUrls = socialMediaUrls;
        this.user = user;
    }

    public Profile() {
        // Default constructor for JPA
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
