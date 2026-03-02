package com.opentutor.classroomservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;


@Entity
@Table(name = "classrooms")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Teacher Id is required")
    @Column(name = "teacher_id", nullable = false)
    private UUID teacherId;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Subject is required")
    @Column(nullable = false)
    private String subject;

    @Column(name = "banner_image")
    private String bannerImage;

    @Column(name = "invite_code", unique = true)
    private String inviteCode;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    public Classroom(){

    }

    public Classroom(UUID teacherId, String title, String subject, String bannerImage, String inviteCode, Boolean isActive) {
        this.teacherId = teacherId;
        this.title = title;
        this.subject = subject;
        this.bannerImage = bannerImage;
        this.inviteCode = inviteCode;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
