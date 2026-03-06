package com.opentutor.quizservice.controller;

import com.opentutor.quizservice.dto.AttemptRequestDto;
import com.opentutor.quizservice.dto.AttemptResponseDto;
import com.opentutor.quizservice.dto.QuestionRequestDto;
import com.opentutor.quizservice.dto.QuestionResponseDto;
import com.opentutor.quizservice.dto.QuizRequestDto;
import com.opentutor.quizservice.dto.QuizResponceDto;
import com.opentutor.quizservice.dto.QuizUpdateDto;
import com.opentutor.quizservice.service.QuizService;
import com.opentutor.quizservice.util.QuizUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(QuizUtil.QUIZ_BASE_PATH)
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizResponceDto> createQuiz(@Valid @RequestBody QuizRequestDto requestDto) {
        QuizResponceDto responseDto = quizService.createQuiz(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping(QuizUtil.QUIZ_BY_ID_PATH)
    public ResponseEntity<QuizResponceDto> getQuizById(@PathVariable("id") String id) {
        QuizResponceDto responseDto = quizService.getQuizById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(QuizUtil.QUIZ_BY_MODULE_PATH)
    public ResponseEntity<List<QuizResponceDto>> getQuizByModuleId(@PathVariable("moduleId") String moduleId) {
        List<QuizResponceDto> responseDto = quizService.getQuizzesByModuleId(moduleId);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping(QuizUtil.QUIZ_BY_ID_PATH)
    public ResponseEntity<QuizResponceDto> updateQuiz(
            @PathVariable("id") String id,
            @Valid @RequestBody QuizUpdateDto updateDto) {
        QuizResponceDto responseDto = quizService.updateQuiz(id, updateDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping(QuizUtil.QUIZ_BY_ID_PATH)
    public ResponseEntity<Void> deleteQuiz(@PathVariable("id") String id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(QuizUtil.QUESTIONS_BY_QUIZ_PATH)
    public ResponseEntity<QuestionResponseDto> createQuestion(
            @PathVariable("quizId") String quizId,
            @Valid @RequestBody QuestionRequestDto requestDto) {
        QuestionResponseDto responseDto = quizService.createQuestion(quizId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping(QuizUtil.QUESTION_BY_ID_PATH)
    public ResponseEntity<QuestionResponseDto> getQuestionById(@PathVariable("id") String id) {
        QuestionResponseDto responseDto = quizService.getQuestionById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(QuizUtil.QUESTIONS_BY_QUIZ_PATH)
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsByQuizId(@PathVariable("quizId") String quizId) {
        List<QuestionResponseDto> responseDto = quizService.getQuestionsByQuizId(quizId);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping(QuizUtil.QUESTION_BY_ID_PATH)
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") String id) {
        quizService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(QuizUtil.ATTEMPTS_BY_QUIZ_PATH)
    public ResponseEntity<AttemptResponseDto> createAttempt(
            @PathVariable("quizId") String quizId,
            @Valid @RequestBody AttemptRequestDto requestDto) {
        AttemptResponseDto responseDto = quizService.createAttempt(quizId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping(QuizUtil.ATTEMPTS_BY_QUIZ_PATH)
    public ResponseEntity<List<AttemptResponseDto>> getAttemptsByQuizId(@PathVariable("quizId") String quizId) {
        List<AttemptResponseDto> responseDto = quizService.getAttemptsByQuizId(quizId);
        return ResponseEntity.ok(responseDto);
    }
}
