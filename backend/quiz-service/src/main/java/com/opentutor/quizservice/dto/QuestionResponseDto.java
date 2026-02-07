package com.opentutor.quizservice.dto;

import java.util.UUID;

public class QuestionResponseDto {
    private UUID id;
    private UUID quizId;
    private String text;
    private String type;
    private String options;

    public QuestionResponseDto() {
    }

    public QuestionResponseDto(UUID id, UUID quizId, String text, String type, String options) {
        this.id = id;
        this.quizId = quizId;
        this.text = text;
        this.type = type;
        this.options = options;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getQuizId() {
        return quizId;
    }

    public void setQuizId(UUID quizId) {
        this.quizId = quizId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
