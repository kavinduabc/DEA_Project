package com.opentutor.announcement_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Message is required")
    @Column(nullable = false, length = 1000)
    private String message;

    @NotNull(message = "Classroom ID is required")
    @Column(name = "classroom_id", nullable = false)
    private UUID classroomId;

    @NotNull(message = "Teacher ID is required")
    @Column(name = "teacher_id", nullable = false)
    private UUID teacherId;

    @Column(name = "share_token", unique = true)
    private String shareToken;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Announcement() {
    }

    public Announcement(UUID id, String title, String message, UUID classroomId, UUID teacherId, String shareToken, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.classroomId = classroomId;
        this.teacherId = teacherId;
        this.shareToken = shareToken;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    // Getters and Setters

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
