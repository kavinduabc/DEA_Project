package com.opentutor.quizservice.controller;

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
}
