package com.opentuter.assignment_service.controller;

import com.opentuter.assignment_service.dto.SubmissionRequestDTO;
import com.opentuter.assignment_service.dto.SubmissionResponseDTO;
import com.opentuter.assignment_service.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.UUID;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<SubmissionResponseDTO> submitAssignment(@RequestBody SubmissionRequestDTO request) {
        return ResponseEntity.ok(submissionService.submitAssignment(request));
    }

    @PutMapping("/{id}/grade")
    public ResponseEntity<SubmissionResponseDTO> gradeSubmission(@PathVariable UUID id,
            @RequestParam Double grade,
            @RequestParam String feedback) {
        return ResponseEntity.ok(submissionService.gradeSubmission(id, grade, feedback));
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<SubmissionResponseDTO>> getSubmissionsByAssignment(@PathVariable UUID assignmentId) {
        return ResponseEntity.ok(submissionService.getSubmissionsByAssignment(assignmentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SubmissionResponseDTO>> getSubmissionsByStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(submissionService.getSubmissionsByStudent(studentId));
    }
}
