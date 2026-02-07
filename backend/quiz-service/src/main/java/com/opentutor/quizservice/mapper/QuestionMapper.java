package com.opentutor.quizservice.mapper;

import com.opentutor.quizservice.dto.QuestionRequestDto;
import com.opentutor.quizservice.dto.QuestionResponseDto;
import com.opentutor.quizservice.model.Question;
import com.opentutor.quizservice.model.Quiz;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    public Question toEntity(QuestionRequestDto dto, Quiz quiz) {
        Question question = new Question();
        question.setQuiz(quiz);
        question.setText(dto.getText());
        question.setType(dto.getType());
        question.setOptions(dto.getOptions());
        return question;
    }

    public QuestionResponseDto toDto(Question question) {
        return new QuestionResponseDto(
            question.getId(),
            question.getQuiz().getId(),
            question.getText(),
            question.getType(),
            question.getOptions()
        );
    }
}
