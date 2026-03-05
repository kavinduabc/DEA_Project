package com.opentutor.qaservice.mapper;

import com.opentutor.qaservice.dto.AnswerRequestDto;
import com.opentutor.qaservice.dto.AnswerResponseDto;
import com.opentutor.qaservice.model.Answer;
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