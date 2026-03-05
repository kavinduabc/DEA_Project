package com.opentuter.assignmentservice.controller;

import com.opentuter.assignmentservice.dto.AssignmentRequestDTO;
import com.opentuter.assignmentservice.dto.AssignmentResponseDTO;
import com.opentuter.assignmentservice.service.IAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller that exposes HTTP endpoints for managing assignments.
 *
 * <p>
 * Base path: {@code /api/assignments}
 * </p>
 */
@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    /** Service layer handling all assignment and submission business logic. */
    private final IAssignmentService assignmentService;

    // ── Health ────────────────────────────────────────────────────────────────

    /**
     * Simple health-check endpoint.
     *
     * @return a plain-text confirmation that the service is running
     */
    @GetMapping("/health")
    public String health() {
        return "Assignment Service is UP";
    }

    // ── Assignment Endpoints ──────────────────────────────────────────────────

    /**
     * Creates a new assignment.
     *
     * @param request DTO containing assignment details
     * @return {@code 201 Created} with the saved assignment
     */
    @PostMapping
    public ResponseEntity<AssignmentResponseDTO> createAssignment(@RequestBody AssignmentRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentService.createAssignment(request));
    }

    /**
     * Retrieves all assignments.
     *
     * @return {@code 200 OK} with the list of all assignments
     */
    @GetMapping
    public ResponseEntity<List<AssignmentResponseDTO>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }

    /**
     * Retrieves a single assignment by its ID.
     *
     * @param id the UUID of the assignment
     * @return {@code 200 OK} with the assignment, or {@code 404 Not Found} if it
     *         does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponseDTO> getAssignmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(assignmentService.getAssignmentById(id));
    }

    /**
     * Retrieves all assignments belonging to a specific classroom.
     *
     * @param classroomId the UUID of the classroom
     * @return {@code 200 OK} with the list of assignments for that classroom
     */
    @GetMapping("/{classroomId}/all")
    public ResponseEntity<List<AssignmentResponseDTO>> getAssignmentsByClassroom(@PathVariable UUID classroomId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByClassroom(classroomId));
    }

    /**
     * Updates an existing assignment.
     *
     * @param id      the UUID of the assignment to update
     * @param request DTO containing the updated assignment details
     * @return {@code 200 OK} with the updated assignment, or {@code 404 Not Found}
     *         if it does not exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<AssignmentResponseDTO> updateAssignment(@PathVariable UUID id,
            @RequestBody AssignmentRequestDTO request) {
        return ResponseEntity.ok(assignmentService.updateAssignment(id, request));
    }

    /**
     * Deletes an assignment by its ID.
     *
     * @param id the UUID of the assignment to delete
     * @return {@code 204 No Content} on success, or {@code 404 Not Found} if it
     *         does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable UUID id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
