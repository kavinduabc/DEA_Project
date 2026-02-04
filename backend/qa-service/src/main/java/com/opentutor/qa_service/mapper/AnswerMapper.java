package com.opentutor.qa_service.mapper;

import com.opentutor.qa_service.dto.AnswerRequestDto;
import com.opentutor.qa_service.dto.AnswerResponseDto;
import com.opentutor.qa_service.model.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {

    // Convert Entity to Response DTO
    public AnswerResponseDto toResponseDto(Answer answer) {
        if (answer == null) {
            return null;
        }

        return new AnswerResponseDto(
                answer.getId(),
                answer.getContent(),
                answer.getQuestionId(),
                answer.getUserId(),
                answer.getUserName(),
                answer.getUpvotes(),
                answer.getIsAccepted(),
                answer.getCreatedAt(),
                answer.getUpdatedAt()
        );
    }

    // Convert Request DTO to Entity
    public Answer toEntity(AnswerRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }

        return new Answer(
                requestDto.getContent(),
                requestDto.getQuestionId(),
                requestDto.getUserId(),
                requestDto.getUserName()
        );
    }
}