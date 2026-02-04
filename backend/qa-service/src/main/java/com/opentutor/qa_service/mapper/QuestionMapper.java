package com.opentutor.qa_service.mapper;

import com.opentutor.qa_service.dto.QuestionRequestDto;
import com.opentutor.qa_service.dto.QuestionResponseDto;
import com.opentutor.qa_service.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    @Autowired
    private AnswerMapper answerMapper;

    // Convert Entity to Response DTO
    public QuestionResponseDto toResponseDto(Question question) {
        if (question == null) {
            return null;
        }

        QuestionResponseDto dto = new QuestionResponseDto(
                question.getId(),
                question.getTitle(),
                question.getContent(),
                question.getUserId(),
                question.getUserName(),
                question.getClassroomId(),
                question.getTags(),
                question.getViewCount(),
                question.getAnswerCount(),
                question.getIsResolved(),
                question.getResolvedAnswerId(),
                question.getCreatedAt(),
                question.getUpdatedAt()
        );

        // Map answers if they exist
        if (question.getAnswers() != null && !question.getAnswers().isEmpty()) {
            dto.setAnswers(
                question.getAnswers().stream()
                    .map(answerMapper::toResponseDto)
                    .collect(Collectors.toList())
            );
        } else {
            dto.setAnswers(new ArrayList<>());
        }

        return dto;
    }

    // Convert Response DTO without answers (for list views)
    public QuestionResponseDto toResponseDtoWithoutAnswers(Question question) {
        if (question == null) {
            return null;
        }

        return new QuestionResponseDto(
                question.getId(),
                question.getTitle(),
                question.getContent(),
                question.getUserId(),
                question.getUserName(),
                question.getClassroomId(),
                question.getTags(),
                question.getViewCount(),
                question.getAnswerCount(),
                question.getIsResolved(),
                question.getResolvedAnswerId(),
                question.getCreatedAt(),
                question.getUpdatedAt()
        );
    }

    // Convert Request DTO to Entity
    public Question toEntity(QuestionRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }

        return new Question(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getUserId(),
                requestDto.getUserName(),
                requestDto.getClassroomId(),
                requestDto.getTags()
        );
    }
}