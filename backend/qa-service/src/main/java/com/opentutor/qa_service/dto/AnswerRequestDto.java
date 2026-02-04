package com.opentutor.qa_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AnswerRequestDto {

    @NotBlank(message = "Content is required")
    private String content;

    @NotNull(message = "Question ID is required")
    private String questionId;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "User name is required")
    private String userName;

    // Constructors
    public AnswerRequestDto() {
    }

    public AnswerRequestDto(String content, String questionId, Long userId, String userName) {
        this.content = content;
        this.questionId = questionId;
        this.userId = userId;
        this.userName = userName;
    }

    // Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
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
}