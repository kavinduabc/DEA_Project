package com.opentutor.quizservice.service;

import com.opentutor.quizservice.dto.AttemptRequestDto;
import com.opentutor.quizservice.dto.AttemptResponseDto;
import com.opentutor.quizservice.dto.QuestionRequestDto;
import com.opentutor.quizservice.dto.QuestionResponseDto;
import com.opentutor.quizservice.dto.QuizRequestDto;
import com.opentutor.quizservice.dto.QuizResponceDto;
import com.opentutor.quizservice.dto.QuizUpdateDto;

import java.util.List;

public interface QuizService {

    QuizResponceDto createQuiz(QuizRequestDto requestDto);
    QuizResponceDto getQuizById(String id);
    List<QuizResponceDto> getQuizzesByModuleId(String moduleId);
    QuizResponceDto updateQuiz(String id, QuizUpdateDto updateDto);
    void deleteQuiz(String id);
    QuestionResponseDto createQuestion(String quizId, QuestionRequestDto requestDto);
    QuestionResponseDto getQuestionById(String id);
    List<QuestionResponseDto> getQuestionsByQuizId(String quizId);
    void deleteQuestion(String id);
    AttemptResponseDto createAttempt(String quizId, AttemptRequestDto requestDto);
    List<AttemptResponseDto> getAttemptsByQuizId(String quizId);
}
