package com.opentuter.assignmentservice.service;

import com.opentuter.assignmentservice.dto.AssignmentRequestDTO;
import com.opentuter.assignmentservice.dto.AssignmentResponseDTO;
import com.opentuter.assignmentservice.dto.SubmissionRequestDTO;
import com.opentuter.assignmentservice.dto.SubmissionResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * Service interface defining the contract for all assignment and submission
 * operations.
 *
 * <p>
 * Implemented by {@link AssignmentServiceImpl}.
 * </p>
 */
public interface IAssignmentService {

    // ── Assignment Operations ─────────────────────────────────────────────────

    /**
     * Creates and saves a new assignment.
     *
     * @param request DTO containing the details of the assignment to create
     * @return the saved assignment as a response DTO
     */
    AssignmentResponseDTO createAssignment(AssignmentRequestDTO request);

    /**
     * Retrieves a single assignment by its unique ID.
     *
     * @param id the UUID of the assignment
     * @return the matching assignment as a response DTO
     * @throws com.opentuter.assignmentservice.exception.ResourceNotFoundException if
     *                                                                             not
     *                                                                             found
     */
    AssignmentResponseDTO getAssignmentById(UUID id);

    /**
     * Retrieves all assignments associated with a specific classroom.
     *
     * @param classroomId the UUID of the classroom
     * @return list of assignments belonging to that classroom
     */
    List<AssignmentResponseDTO> getAssignmentsByClassroom(UUID classroomId);

    /**
     * Retrieves all assignments in the system.
     *
     * @return list of all assignments
     */
    List<AssignmentResponseDTO> getAllAssignments();

    /**
     * Updates an existing assignment's details.
     *
     * @param id      the UUID of the assignment to update
     * @param request DTO containing the updated fields
     * @return the updated assignment as a response DTO
     * @throws com.opentuter.assignmentservice.exception.ResourceNotFoundException if
     *                                                                             not
     *                                                                             found
     */
    AssignmentResponseDTO updateAssignment(UUID id, AssignmentRequestDTO request);

    /**
     * Deletes an assignment by its ID.
     *
     * @param id the UUID of the assignment to delete
     * @throws com.opentuter.assignmentservice.exception.ResourceNotFoundException if
     *                                                                             not
     *                                                                             found
     */
    void deleteAssignment(UUID id);

    // ── Submission Operations ─────────────────────────────────────────────────

    /**
     * Submits an assignment on behalf of a student.
     *
     * @param request DTO containing submission details (assignment ID, student ID,
     *                file URL)
     * @return the saved submission as a response DTO
     * @throws com.opentuter.assignmentservice.exception.ResourceNotFoundException  if
     *                                                                              the
     *                                                                              assignment
     *                                                                              does
     *                                                                              not
     *                                                                              exist
     * @throws com.opentuter.assignmentservice.exception.SubmissionExpiredException if
     *                                                                              the
     *                                                                              due
     *                                                                              date
     *                                                                              has
     *                                                                              passed
     */
    SubmissionResponseDTO submitAssignment(SubmissionRequestDTO request);

    /**
     * Retrieves a single submission by its unique ID.
     *
     * @param id the UUID of the submission
     * @return the matching submission as a response DTO
     * @throws com.opentuter.assignmentservice.exception.ResourceNotFoundException if
     *                                                                             not
     *                                                                             found
     */
    SubmissionResponseDTO getSubmissionById(UUID id);

    /**
     * Records a grade and feedback for an existing submission.
     *
     * @param id       the UUID of the submission
     * @param grade    the numeric grade
     * @param feedback the textual feedback for the student
     * @return the updated submission as a response DTO
     * @throws com.opentuter.assignmentservice.exception.ResourceNotFoundException if
     *                                                                             not
     *                                                                             found
     */
    SubmissionResponseDTO gradeSubmission(UUID id, Double grade, String feedback);

    /**
     * Retrieves all submissions for a given assignment.
     *
     * @param assignmentId the UUID of the assignment
     * @return list of submissions for that assignment
     */
    List<SubmissionResponseDTO> getSubmissionsByAssignment(UUID assignmentId);

    /**
     * Retrieves all submissions made by a specific student.
     *
     * @param studentId the UUID of the student
     * @return list of submissions by that student
     */
    List<SubmissionResponseDTO> getSubmissionsByStudent(UUID studentId);
}
