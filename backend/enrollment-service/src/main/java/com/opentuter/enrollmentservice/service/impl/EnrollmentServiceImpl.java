package com.opentuter.enrollmentservice.service.impl;

import com.opentuter.enrollmentservice.client.ClassroomServiceClient;
import com.opentuter.enrollmentservice.client.UserServiceClient;
import com.opentuter.enrollmentservice.dto.EnrollmentRequestDTO;
import com.opentuter.enrollmentservice.dto.EnrollmentResponseDTO;
import com.opentuter.enrollmentservice.exception.DuplicateResourceException;
import com.opentuter.enrollmentservice.exception.ResourceNotFoundException;
import com.opentuter.enrollmentservice.mapper.EnrollmentMapper;
import com.opentuter.enrollmentservice.model.Enrollment;
import com.opentuter.enrollmentservice.repository.EnrollmentRepository;
import com.opentuter.enrollmentservice.service.EnrollmentService;
import com.opentuter.enrollmentservice.util.EnrollmentUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final UserServiceClient userServiceClient;
    private final ClassroomServiceClient classroomServiceClient;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 EnrollmentMapper enrollmentMapper,
                                 UserServiceClient userServiceClient,
                                 ClassroomServiceClient classroomServiceClient) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
        this.userServiceClient = userServiceClient;
        this.classroomServiceClient = classroomServiceClient;
    }

    @Override
    public EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO dto) {
        if (dto.getStudentId() == null) {
            throw new IllegalArgumentException(EnrollmentUtil.ERROR_NULL_FIELDS);
        }

        // Validate that user exists in profile-service
        if (!userServiceClient.userExists(dto.getStudentId())) {
            throw new ResourceNotFoundException(EnrollmentUtil.RESOURCE_USER, EnrollmentUtil.FIELD_ID, dto.getStudentId());
        }

        // Validate that classroom exists in classroom-service
        if (!classroomServiceClient.classroomExists(dto.getClassroomId())) {
            throw new ResourceNotFoundException(EnrollmentUtil.RESOURCE_CLASSROOM, EnrollmentUtil.FIELD_ID, dto.getClassroomId());
        }

        if (enrollmentRepository.existsByStudentIdAndClassroomId(
                dto.getStudentId(), dto.getClassroomId())) {
            throw new DuplicateResourceException(
                    EnrollmentUtil.RESOURCE_ENROLLMENT,
                    EnrollmentUtil.FIELD_STUDENT_CLASSROOM,
                    dto.getStudentId() + EnrollmentUtil.PATH_SEPARATOR + dto.getClassroomId()
            );
        }

        Enrollment enrollment = enrollmentMapper.toEntity(dto);
        Enrollment saved = enrollmentRepository.save(enrollment);

        return enrollmentMapper.toResponseDTO(saved);
    }

    @Override
    public List<EnrollmentResponseDTO> getAllEnrollments() {
        return enrollmentMapper.toResponseDTOList(enrollmentRepository.findAll());
    }

    @Override
    public Optional<EnrollmentResponseDTO> getEnrollmentById(UUID id) {
        return enrollmentRepository.findById(id)
                .map(enrollmentMapper::toResponseDTO);
    }

    @Override
    public List<EnrollmentResponseDTO> getEnrollmentsByStudentId(UUID studentId) {
        return enrollmentMapper.toResponseDTOList(
                enrollmentRepository.findByStudentId(studentId));
    }

    @Override
    public List<EnrollmentResponseDTO> getEnrollmentsByClassroomId(UUID classroomId) {
        return enrollmentMapper.toResponseDTOList(
                enrollmentRepository.findByClassroomId(classroomId));
    }

    @Override
    public void deleteEnrollment(UUID id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(EnrollmentUtil.RESOURCE_ENROLLMENT, EnrollmentUtil.FIELD_ID, id));
        enrollmentRepository.delete(enrollment);
    }
}
