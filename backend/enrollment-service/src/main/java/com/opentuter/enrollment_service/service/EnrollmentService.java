//package com.opentuter.enrollment_service.service;
//
//import com.opentuter.enrollment_service.dto.EnrollmentRequestDTO;
//import com.opentuter.enrollment_service.dto.EnrollmentResponseDTO;
//import com.opentuter.enrollment_service.exception.DuplicateResourceException;
//import com.opentuter.enrollment_service.exception.ResourceNotFoundException;
//import com.opentuter.enrollment_service.mapper.EnrollmentMapper;
//import com.opentuter.enrollment_service.model.Enrollment;
//import com.opentuter.enrollment_service.repository.EnrollmentRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class EnrollmentService {
//
//    private final EnrollmentRepository enrollmentRepository;
//    private final EnrollmentMapper enrollmentMapper;
//
//    public EnrollmentService(EnrollmentRepository enrollmentRepository,
//                             EnrollmentMapper enrollmentMapper) {
//        this.enrollmentRepository = enrollmentRepository;
//        this.enrollmentMapper = enrollmentMapper;
//    }
//
//    // Create enrollment
//    public EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO requestDTO) {
//        if (enrollmentRepository.existsByStudentIdAndClassroomId(
//                requestDTO.getStudentId(), requestDTO.getClassroomId())) {
//            throw new DuplicateResourceException(
//                    "Enrollment",
//                    "studentId + classroomId",
//                    requestDTO.getStudentId() + "/" + requestDTO.getClassroomId()
//            );
//        }
//
//        Enrollment entity = enrollmentMapper.toEntity(requestDTO);
//        Enrollment saved = enrollmentRepository.save(entity);
//        return enrollmentMapper.toResponseDTO(saved);
//    }
//
//    // Get all enrollments
//    public List<EnrollmentResponseDTO> getAllEnrollments() {
//        return enrollmentMapper.toResponseDTOList(enrollmentRepository.findAll());
//    }
//
//    // Get enrollment by ID
//    public Optional<EnrollmentResponseDTO> getEnrollmentById(UUID id) {
//        return enrollmentRepository.findById(id).map(enrollmentMapper::toResponseDTO);
//    }
//
//    // Get enrollments by student
//    public List<EnrollmentResponseDTO> getEnrollmentsByStudentId(UUID studentId) {
//        return enrollmentMapper.toResponseDTOList(enrollmentRepository.findByStudentId(studentId));
//    }
//
//    // Get enrollments by classroom
//    public List<EnrollmentResponseDTO> getEnrollmentsByClassroomId(UUID classroomId) {
//        return enrollmentMapper.toResponseDTOList(enrollmentRepository.findByClassroomId(classroomId));
//    }
//
//    // Delete enrollment
//    public void deleteEnrollment(UUID id) {
//        Enrollment entity = enrollmentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", "id", id));
//        enrollmentRepository.delete(entity);
//    }
//}

package com.opentuter.enrollment_service.service;

import com.opentuter.enrollment_service.dto.EnrollmentRequestDTO;
import com.opentuter.enrollment_service.dto.EnrollmentResponseDTO;
import com.opentuter.enrollment_service.exception.DuplicateResourceException;
import com.opentuter.enrollment_service.exception.ResourceNotFoundException;
import com.opentuter.enrollment_service.mapper.EnrollmentMapper;
import com.opentuter.enrollment_service.model.Enrollment;
import com.opentuter.enrollment_service.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    public EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO dto) {

        if (dto.getStudentId() == null || dto.getClassroomId() == null) {
            throw new IllegalArgumentException("studentId and classroomId must not be null");
        }

        if (enrollmentRepository.existsByStudentIdAndClassroomId(
                dto.getStudentId(), dto.getClassroomId())) {
            throw new DuplicateResourceException(
                    "Enrollment",
                    "studentId + classroomId",
                    dto.getStudentId() + "/" + dto.getClassroomId()
            );
        }

        Enrollment enrollment = enrollmentMapper.toEntity(dto);
        Enrollment saved = enrollmentRepository.save(enrollment);

        return enrollmentMapper.toResponseDTO(saved);
    }

    public List<EnrollmentResponseDTO> getAllEnrollments() {
        return enrollmentMapper.toResponseDTOList(enrollmentRepository.findAll());
    }

    public Optional<EnrollmentResponseDTO> getEnrollmentById(UUID id) {
        return enrollmentRepository.findById(id)
                .map(enrollmentMapper::toResponseDTO);
    }

    public List<EnrollmentResponseDTO> getEnrollmentsByStudentId(UUID studentId) {
        return enrollmentMapper.toResponseDTOList(
                enrollmentRepository.findByStudentId(studentId));
    }

    public List<EnrollmentResponseDTO> getEnrollmentsByClassroomId(UUID classroomId) {
        return enrollmentMapper.toResponseDTOList(
                enrollmentRepository.findByClassroomId(classroomId));
    }

    public void deleteEnrollment(UUID id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Enrollment", "id", id));
        enrollmentRepository.delete(enrollment);
    }
}
