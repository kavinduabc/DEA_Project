package com.opentutor.classroomservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public class ClassroomRequestDTO {

    @NotNull(message = "Teacher ID is required")
    private UUID teacherId;

    @NotBlank(message = "Title is required")
    @Length(min = 3, max = 100, message = "Title should be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Subject is required")
    @Length(min = 3, max = 50, message = "Subject should be between 3 and 50 characters")
    private String subject;

    private String bannerImage;

    @Length(min = 6, max = 10, message = "Invite code should be between 6 and 10 characters")
    private String inviteCode;

    public ClassroomRequestDTO(UUID teacherId, String title, String subject, String bannerImage, String inviteCode) {
        this.teacherId = teacherId;
        this.title = title;
        this.subject = subject;
        this.bannerImage = bannerImage;
        this.inviteCode = inviteCode;
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
}
