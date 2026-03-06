package com.opentutor.qaservice.service;

import com.opentutor.qaservice.dto.AnswerRequestDto;
import com.opentutor.qaservice.dto.AnswerResponseDto;
import com.opentutor.qaservice.dto.AnswerUpdateDto;
import com.opentutor.qaservice.dto.QuestionRequestDto;
import com.opentutor.qaservice.dto.QuestionResponseDto;
import com.opentutor.qaservice.dto.QuestionUpdateDto;

import java.util.List;
import java.util.UUID;

public interface QaService {

    QuestionResponseDto createQuestion(QuestionRequestDto requestDto);

    QuestionResponseDto getQuestionById(String id);

    List<QuestionResponseDto> getAllQuestions();

    List<QuestionResponseDto> getQuestionsByClassroomId(String classroomId);

    List<QuestionResponseDto> getQuestionsByUserId(UUID userId);

    List<QuestionResponseDto> getUnresolvedQuestions();

    List<QuestionResponseDto> getUnresolvedQuestionsByClassroomId(String classroomId);

    List<QuestionResponseDto> searchQuestions(String keyword);

    QuestionResponseDto updateQuestion(String id, QuestionUpdateDto updateDto);

    QuestionResponseDto markAsResolved(String questionId, String answerId);

    QuestionResponseDto markAsUnresolved(String questionId);

    void deleteQuestion(String id);

    void incrementAnswerCount(String questionId);

    void decrementAnswerCount(String questionId);

    AnswerResponseDto createAnswer(AnswerRequestDto requestDto);

    AnswerResponseDto getAnswerById(String id);

    List<AnswerResponseDto> getAnswersByQuestionId(String questionId);

    List<AnswerResponseDto> getAnswersByUserId(UUID userId);

    AnswerResponseDto updateAnswer(String id, AnswerUpdateDto updateDto);

    AnswerResponseDto markAsAccepted(String answerId);

    AnswerResponseDto unmarkAsAccepted(String answerId);

    AnswerResponseDto upvoteAnswer(String answerId);

    AnswerResponseDto removeUpvote(String answerId);

    void deleteAnswer(String id);
}