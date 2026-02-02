package com.opentutor.qa_service.dto;

public class QuestionUpdateDto {

    private String title;
    private String content;
    private String tags;

    // Constructors
    public QuestionUpdateDto() {
    }

    public QuestionUpdateDto(String title, String content, String tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    // Getters and Setters
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}