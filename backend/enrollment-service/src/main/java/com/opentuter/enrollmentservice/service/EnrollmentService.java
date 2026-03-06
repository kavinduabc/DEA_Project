package com.opentuter.enrollmentservice.service;

import com.opentuter.enrollmentservice.dto.EnrollmentRequestDTO;
import com.opentuter.enrollmentservice.dto.EnrollmentResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnrollmentService {

    EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO dto);

    List<EnrollmentResponseDTO> getAllEnrollments();

    Optional<EnrollmentResponseDTO> getEnrollmentById(UUID id);

    List<EnrollmentResponseDTO> getEnrollmentsByStudentId(UUID studentId);

    List<EnrollmentResponseDTO> getEnrollmentsByClassroomId(int classroomId);

    void deleteEnrollment(UUID id);
}
