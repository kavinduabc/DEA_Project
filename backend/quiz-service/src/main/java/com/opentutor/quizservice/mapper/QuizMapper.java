package com.opentutor.quizservice.mapper;

import com.opentutor.quizservice.dto.QuizRequestDto;
import com.opentutor.quizservice.dto.QuizResponceDto;
import com.opentutor.quizservice.model.Quiz;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public Quiz toEntity(QuizRequestDto dto) {
        Quiz quiz = new Quiz();
        quiz.setModuleId(dto.getModuleId());
        quiz.setTitle(dto.getTitle());
        quiz.setTimeLimit(dto.getTimeLimit());
        quiz.setPassingScore(dto.getPassingScore());
        return quiz;
    }

    public QuizResponceDto toDto(Quiz quiz) {
        return new QuizResponceDto(
            quiz.getId(),
            quiz.getModuleId(),
            quiz.getTitle(),
            quiz.getTimeLimit(),
            quiz.getPassingScore()
        );
    }
}
