package com.opentutor.classroomservice.dto;

import java.util.UUID;

public class ClassroomResponseDTO {

    private Long id;
    private UUID teacherId;
    private String title;
    private String subject;
    private String bannerImage;
    private String inviteCode;
    private Boolean isActive;

    public ClassroomResponseDTO() {
    }

    public ClassroomResponseDTO(Long id, UUID teacherId, String title, String subject, String bannerImage, String inviteCode, Boolean isActive) {
        this.id = id;
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
