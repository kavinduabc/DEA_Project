package com.opentutor.qa_service.controller;

import com.opentutor.qa_service.dto.QuestionRequestDto;
import com.opentutor.qa_service.dto.QuestionResponseDto;
import com.opentutor.qa_service.dto.QuestionUpdateDto;
import com.opentutor.qa_service.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qa/questions")
@CrossOrigin(origins = "*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * Create a new question
     * POST /api/qa/questions
     */
    @PostMapping
    public ResponseEntity<QuestionResponseDto> createQuestion(@Valid @RequestBody QuestionRequestDto requestDto) {
        QuestionResponseDto response = questionService.createQuestion(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Get question by ID
     * GET /api/qa/questions/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> getQuestionById(@PathVariable String id) {
        QuestionResponseDto response = questionService.getQuestionById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all questions
     * GET /api/qa/questions
     */
    @GetMapping
    public ResponseEntity<List<QuestionResponseDto>> getAllQuestions() {
        List<QuestionResponseDto> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    /**
     * Get questions by classroom ID
     * GET /api/qa/questions/classroom/{classroomId}
     */
    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsByClassroomId(@PathVariable String classroomId) {
        List<QuestionResponseDto> questions = questionService.getQuestionsByClassroomId(classroomId);
        return ResponseEntity.ok(questions);
    }

    /**
     * Get questions by user ID
     * GET /api/qa/questions/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsByUserId(@PathVariable Long userId) {
        List<QuestionResponseDto> questions = questionService.getQuestionsByUserId(userId);
        return ResponseEntity.ok(questions);
    }

    /**
     * Get unresolved questions
     * GET /api/qa/questions/unresolved
     */
    @GetMapping("/unresolved")
    public ResponseEntity<List<QuestionResponseDto>> getUnresolvedQuestions() {
        List<QuestionResponseDto> questions = questionService.getUnresolvedQuestions();
        return ResponseEntity.ok(questions);
    }

    /**
     * Get unresolved questions by classroom ID
     * GET /api/qa/questions/classroom/{classroomId}/unresolved
     */
    @GetMapping("/classroom/{classroomId}/unresolved")
    public ResponseEntity<List<QuestionResponseDto>> getUnresolvedQuestionsByClassroomId(@PathVariable String classroomId) {
        List<QuestionResponseDto> questions = questionService.getUnresolvedQuestionsByClassroomId(classroomId);
        return ResponseEntity.ok(questions);
    }

    /**
     * Search questions
     * GET /api/qa/questions/search?keyword={keyword}
     */
    @GetMapping("/search")
    public ResponseEntity<List<QuestionResponseDto>> searchQuestions(@RequestParam String keyword) {
        List<QuestionResponseDto> questions = questionService.searchQuestions(keyword);
        return ResponseEntity.ok(questions);
    }

    /**
     * Update a question
     * PUT /api/qa/questions/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> updateQuestion(
            @PathVariable String id,
            @RequestBody QuestionUpdateDto updateDto) {
        QuestionResponseDto response = questionService.updateQuestion(id, updateDto);
        return ResponseEntity.ok(response);
    }

    /**
     * Mark question as resolved
     * PATCH /api/qa/questions/{questionId}/resolve/{answerId}
     */
    @PatchMapping("/{questionId}/resolve/{answerId}")
    public ResponseEntity<QuestionResponseDto> markAsResolved(
            @PathVariable String questionId,
            @PathVariable String answerId) {
        QuestionResponseDto response = questionService.markAsResolved(questionId, answerId);
        return ResponseEntity.ok(response);
    }

    /**
     * Mark question as unresolved
     * PATCH /api/qa/questions/{questionId}/unresolve
     */
    @PatchMapping("/{questionId}/unresolve")
    public ResponseEntity<QuestionResponseDto> markAsUnresolved(@PathVariable String questionId) {
        QuestionResponseDto response = questionService.markAsUnresolved(questionId);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a question
     * DELETE /api/qa/questions/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable String id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("Question with ID " + id + " has been deleted successfully.");
    }
}