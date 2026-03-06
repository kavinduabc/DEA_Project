package com.opentuter.assignmentservice.service;

import com.opentuter.assignmentservice.dto.AssignmentRequestDTO;
import com.opentuter.assignmentservice.dto.AssignmentResponseDTO;
import com.opentuter.assignmentservice.dto.SubmissionRequestDTO;
import com.opentuter.assignmentservice.dto.SubmissionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AssignmentService {

    // --- Assignment Operations ---

    AssignmentResponseDTO createAssignment(AssignmentRequestDTO request);

    AssignmentResponseDTO getAssignmentById(UUID id);

    List<AssignmentResponseDTO> getAssignmentsByClassroom(UUID classroomId);

    List<AssignmentResponseDTO> getAllAssignments();

    AssignmentResponseDTO updateAssignment(UUID id, AssignmentRequestDTO request);

    void deleteAssignment(UUID id);

    // --- Submission Operations ---

    SubmissionResponseDTO submitAssignment(SubmissionRequestDTO request);

    SubmissionResponseDTO gradeSubmission(UUID id, Double grade, String feedback);

    List<SubmissionResponseDTO> getSubmissionsByAssignment(UUID assignmentId);

    List<SubmissionResponseDTO> getSubmissionsByStudent(UUID studentId);
}
