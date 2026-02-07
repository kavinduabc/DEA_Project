package com.opentutor.quizservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AttemptRequestDto {
    @NotBlank(message = "Student ID is required")
    private String studentId;

    @NotNull(message = "Score is required")
    private Double score;

    public AttemptRequestDto() {
    }

    public AttemptRequestDto(String studentId, Double score) {
        this.studentId = studentId;
        this.score = score;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
