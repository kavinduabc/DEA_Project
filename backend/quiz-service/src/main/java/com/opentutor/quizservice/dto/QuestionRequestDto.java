package com.opentutor.quizservice.dto;

import jakarta.validation.constraints.NotBlank;

public class QuestionRequestDto {
    @NotBlank(message = "Text is required")
    private String text;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Options are required")
    private String options;

    public QuestionRequestDto() {
    }

    public QuestionRequestDto(String text, String type, String options) {
        this.text = text;
        this.type = type;
        this.options = options;
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
