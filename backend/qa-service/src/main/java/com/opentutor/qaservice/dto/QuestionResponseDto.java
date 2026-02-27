package com.opentutor.qaservice.dto;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionResponseDto {

    private String id;
    private String title;
    private String content;
    private Long userId;
    private String userName;
    private String classroomId;
    private String tags;
    private Integer viewCount;
    private Integer answerCount;
    private Boolean isResolved;
    private String resolvedAnswerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<AnswerResponseDto> answers;

    // Constructors
    public QuestionResponseDto() {
    }

    public QuestionResponseDto(String id, String title, String content, Long userId, String userName, 
                              String classroomId, String tags, Integer viewCount, Integer answerCount, 
                              Boolean isResolved, String resolvedAnswerId, LocalDateTime createdAt, 
                              LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.userName = userName;
        this.classroomId = classroomId;
        this.tags = tags;
        this.viewCount = viewCount;
        this.answerCount = answerCount;
        this.isResolved = isResolved;
        this.resolvedAnswerId = resolvedAnswerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Boolean getIsResolved() {
        return isResolved;
    }

    public void setIsResolved(Boolean resolved) {
        isResolved = resolved;
    }

    public String getResolvedAnswerId() {
        return resolvedAnswerId;
    }

    public void setResolvedAnswerId(String resolvedAnswerId) {
        this.resolvedAnswerId = resolvedAnswerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<AnswerResponseDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerResponseDto> answers) {
        this.answers = answers;
    }
}