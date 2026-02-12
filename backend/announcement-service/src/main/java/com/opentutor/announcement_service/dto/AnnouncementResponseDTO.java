package com.opentutor.announcement_service.dto;

import java.util.UUID;

public class AnnouncementResponseDTO {

    private UUID id;
    private String title;
    private String message;
    private UUID classroomId;
    private UUID teacherId;
    private String shareToken;
    private String createdAt;

    public AnnouncementResponseDTO() {
    }

    public AnnouncementResponseDTO(UUID id, String title, String message, UUID classroomId,
                                   UUID teacherId, String shareToken, String createdAt) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.classroomId = classroomId;
        this.teacherId = teacherId;
        this.shareToken = shareToken;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(UUID classroomId) {
        this.classroomId = classroomId;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public String getShareToken() {
        return shareToken;
    }

    public void setShareToken(String shareToken) {
        this.shareToken = shareToken;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
