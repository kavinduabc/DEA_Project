package com.opentutor.quizservice.controller;

import com.opentutor.quizservice.dto.AttemptRequestDto;
import com.opentutor.quizservice.dto.AttemptResponseDto;
import com.opentutor.quizservice.dto.QuestionRequestDto;
import com.opentutor.quizservice.dto.QuestionResponseDto;
import com.opentutor.quizservice.dto.QuizRequestDto;
import com.opentutor.quizservice.dto.QuizResponceDto;
import com.opentutor.quizservice.dto.QuizUpdateDto;
import com.opentutor.quizservice.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizResponceDto> createQuiz(@Valid @RequestBody QuizRequestDto requestDto) {
        QuizResponceDto responseDto = quizService.createQuiz(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponceDto> getQuizById(@PathVariable("id") String id) {
        QuizResponceDto responseDto = quizService.getQuizById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<QuizResponceDto>> getQuizByModuleId(@PathVariable("moduleId") String moduleId) {
        List<QuizResponceDto> responseDto = quizService.getQuizzesByModuleId(moduleId);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizResponceDto> updateQuiz(
            @PathVariable("id") String id,
            @Valid @RequestBody QuizUpdateDto updateDto) {
        QuizResponceDto responseDto = quizService.updateQuiz(id, updateDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable("id") String id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{quizId}/questions")
    public ResponseEntity<QuestionResponseDto> createQuestion(
            @PathVariable("quizId") String quizId,
            @Valid @RequestBody QuestionRequestDto requestDto) {
        QuestionResponseDto responseDto = quizService.createQuestion(quizId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<QuestionResponseDto> getQuestionById(@PathVariable("id") String id) {
        QuestionResponseDto responseDto = quizService.getQuestionById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{quizId}/questions")
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsByQuizId(@PathVariable("quizId") String quizId) {
        List<QuestionResponseDto> responseDto = quizService.getQuestionsByQuizId(quizId);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") String id) {
        quizService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{quizId}/attempts")
    public ResponseEntity<AttemptResponseDto> createAttempt(
            @PathVariable("quizId") String quizId,
            @Valid @RequestBody AttemptRequestDto requestDto) {
        AttemptResponseDto responseDto = quizService.createAttempt(quizId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{quizId}/attempts")
    public ResponseEntity<List<AttemptResponseDto>> getAttemptsByQuizId(@PathVariable("quizId") String quizId) {
        List<AttemptResponseDto> responseDto = quizService.getAttemptsByQuizId(quizId);
        return ResponseEntity.ok(responseDto);
    }
}
