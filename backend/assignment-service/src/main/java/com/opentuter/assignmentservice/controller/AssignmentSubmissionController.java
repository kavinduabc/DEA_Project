package com.opentuter.assignmentservice.controller;

import com.opentuter.assignmentservice.dto.SubmissionRequestDTO;
import com.opentuter.assignmentservice.dto.SubmissionResponseDTO;
import com.opentuter.assignmentservice.service.IAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller that exposes HTTP endpoints for managing student submissions.
 *
 * <p>
 * Base path: {@code /api/submissions}
 * </p>
 */
@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class AssignmentSubmissionController {

    /** Service layer handling submission business logic. */
    private final IAssignmentService assignmentService;

    /**
     * Submits an assignment on behalf of a student.
     *
     * @param request DTO containing the submission details
     * @return {@code 202 Accepted} with the saved submission,
     *         or {@code 422 Unprocessable Entity} if the due date has passed
     */
    @PostMapping
    public ResponseEntity<SubmissionResponseDTO> submitAssignment(@RequestBody SubmissionRequestDTO request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(assignmentService.submitAssignment(request));
    }

    /**
     * Retrieves a single submission by its ID.
     *
     * @param id the UUID of the submission
     * @return {@code 200 OK} with the submission, or {@code 404 Not Found} if it
     *         does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<SubmissionResponseDTO> getSubmissionById(@PathVariable UUID id) {
        return ResponseEntity.ok(assignmentService.getSubmissionById(id));
    }

    /**
     * Records a grade and feedback for an existing submission.
     *
     * @param id       the UUID of the submission to grade
     * @param grade    the numeric grade to assign
     * @param feedback the textual feedback for the student
     * @return {@code 200 OK} with the updated submission, or {@code 404 Not Found}
     *         if it does not exist
     */
    @PutMapping("/{id}/grade")
    public ResponseEntity<SubmissionResponseDTO> gradeSubmission(@PathVariable UUID id,
            @RequestParam Double grade,
            @RequestParam String feedback) {
        return ResponseEntity.ok(assignmentService.gradeSubmission(id, grade, feedback));
    }

    /**
     * Retrieves all submissions for a given assignment.
     *
     * @param assignmentId the UUID of the assignment
     * @return {@code 200 OK} with the list of submissions
     */
    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<SubmissionResponseDTO>> getSubmissionsByAssignment(@PathVariable UUID assignmentId) {
        return ResponseEntity.ok(assignmentService.getSubmissionsByAssignment(assignmentId));
    }

    /**
     * Retrieves all submissions made by a specific student.
     *
     * @param studentId the UUID of the student
     * @return {@code 200 OK} with the list of submissions
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SubmissionResponseDTO>> getSubmissionsByStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(assignmentService.getSubmissionsByStudent(studentId));
    }
}
