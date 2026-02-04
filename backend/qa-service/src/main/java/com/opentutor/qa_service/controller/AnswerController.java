package com.opentutor.qa_service.controller;

import com.opentutor.qa_service.dto.AnswerRequestDto;
import com.opentutor.qa_service.dto.AnswerResponseDto;
import com.opentutor.qa_service.dto.AnswerUpdateDto;
import com.opentutor.qa_service.service.AnswerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qa/answers")
@CrossOrigin(origins = "*")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    /**
     * Create a new answer
     * POST /api/qa/answers
     */
    @PostMapping
    public ResponseEntity<AnswerResponseDto> createAnswer(@Valid @RequestBody AnswerRequestDto requestDto) {
        AnswerResponseDto response = answerService.createAnswer(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Get answer by ID
     * GET /api/qa/answers/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponseDto> getAnswerById(@PathVariable String id) {
        AnswerResponseDto response = answerService.getAnswerById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all answers for a question
     * GET /api/qa/answers/question/{questionId}
     */
    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<AnswerResponseDto>> getAnswersByQuestionId(@PathVariable String questionId) {
        List<AnswerResponseDto> answers = answerService.getAnswersByQuestionId(questionId);
        return ResponseEntity.ok(answers);
    }

    /**
     * Get answers by user ID
     * GET /api/qa/answers/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AnswerResponseDto>> getAnswersByUserId(@PathVariable Long userId) {
        List<AnswerResponseDto> answers = answerService.getAnswersByUserId(userId);
        return ResponseEntity.ok(answers);
    }

    /**
     * Update an answer
     * PUT /api/qa/answers/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<AnswerResponseDto> updateAnswer(
            @PathVariable String id,
            @RequestBody AnswerUpdateDto updateDto) {
        AnswerResponseDto response = answerService.updateAnswer(id, updateDto);
        return ResponseEntity.ok(response);
    }

    /**
     * Mark answer as accepted
     * PATCH /api/qa/answers/{id}/accept
     */
    @PatchMapping("/{id}/accept")
    public ResponseEntity<AnswerResponseDto> markAsAccepted(@PathVariable String id) {
        AnswerResponseDto response = answerService.markAsAccepted(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Unmark answer as accepted
     * PATCH /api/qa/answers/{id}/unaccept
     */
    @PatchMapping("/{id}/unaccept")
    public ResponseEntity<AnswerResponseDto> unmarkAsAccepted(@PathVariable String id) {
        AnswerResponseDto response = answerService.unmarkAsAccepted(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Upvote an answer
     * PATCH /api/qa/answers/{id}/upvote
     */
    @PatchMapping("/{id}/upvote")
    public ResponseEntity<AnswerResponseDto> upvoteAnswer(@PathVariable String id) {
        AnswerResponseDto response = answerService.upvoteAnswer(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Remove upvote from an answer
     * PATCH /api/qa/answers/{id}/remove-upvote
     */
    @PatchMapping("/{id}/remove-upvote")
    public ResponseEntity<AnswerResponseDto> removeUpvote(@PathVariable String id) {
        AnswerResponseDto response = answerService.removeUpvote(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete an answer
     * DELETE /api/qa/answers/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable String id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.ok("Answer with ID " + id + " has been deleted successfully.");
    }
}