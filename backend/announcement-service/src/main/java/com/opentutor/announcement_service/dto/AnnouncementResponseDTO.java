package com.opentutor.announcement_service.dto;

public class AnnouncementResponseDTO {
      private Long id;
    private String title;
    private String message;
    private Long classroomId;
    private Long createdBy;
    private String createdAt;

    // Constructors
    public AnnouncementResponseDTO() {
    }

    public AnnouncementResponseDTO(Long id, String title, String message,
                                   Long classroomId, Long createdBy, String createdAt) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.classroomId = classroomId;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
