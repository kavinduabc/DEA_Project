package com.opentutor.qa_service.dto;

public class AnswerUpdateDto {

    private String content;

    // Constructors
    public AnswerUpdateDto() {
    }

    public AnswerUpdateDto(String content) {
        this.content = content;
    }

    // Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}