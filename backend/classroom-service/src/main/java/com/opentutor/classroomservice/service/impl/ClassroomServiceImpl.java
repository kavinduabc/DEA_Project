package com.opentutor.classroomservice.service.impl;

import com.opentutor.classroomservice.client.UserServiceClient;
import com.opentutor.classroomservice.dto.ClassroomRequestDTO;
import com.opentutor.classroomservice.dto.ClassroomResponseDTO;
import com.opentutor.classroomservice.exception.DuplicateResourceException;
import com.opentutor.classroomservice.exception.ResourceNotFoundException;
import com.opentutor.classroomservice.mapper.ClassroomMapper;
import com.opentutor.classroomservice.model.Classroom;
import com.opentutor.classroomservice.repository.ClassroomRepository;
import com.opentutor.classroomservice.service.ClassroomService;
import com.opentutor.classroomservice.util.ClassroomUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;
    private final UserServiceClient userServiceClient;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository,
                                ClassroomMapper classroomMapper,
                                UserServiceClient userServiceClient) {
        this.classroomRepository = classroomRepository;
        this.classroomMapper = classroomMapper;
        this.userServiceClient = userServiceClient;
    }

    // Create
    @Override
    public ClassroomResponseDTO createClassroom(ClassroomRequestDTO classroomRequestDTO) {

        // Verify that the teacher (user) exists in the profile-service
        if (!userServiceClient.userExists(classroomRequestDTO.getTeacherId())) {
            throw new ResourceNotFoundException(
                    ClassroomUtil.RESOURCE_USER,
                    ClassroomUtil.FIELD_ID,
                    classroomRequestDTO.getTeacherId()
            );
        }

        // Check if invite code already exists (if provided)
        if (classroomRequestDTO.getInviteCode() != null &
                !classroomRepository.findByInviteCode(classroomRequestDTO.getInviteCode()).isEmpty()) {

            throw new DuplicateResourceException(
                    ClassroomUtil.RESOURCE_CLASSROOM,
                    ClassroomUtil.FIELD_INVITE_CODE,
                    classroomRequestDTO.getInviteCode()
            );

        }

        Classroom entity = classroomMapper.toEntity(classroomRequestDTO);
        Classroom savedEntity = classroomRepository.save(entity);

        return classroomMapper.toResponseDTO(savedEntity);
    }

    // Read all
    @Override
    public List<ClassroomResponseDTO> getAllClassrooms() {
        List<Classroom> entities = classroomRepository.findAll();
        return classroomMapper.toResponseDTOList(entities);
    }

    // Read by ID
    @Override
    public ClassroomResponseDTO getClassroomById(UUID id) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(ClassroomUtil.RESOURCE_CLASSROOM, ClassroomUtil.FIELD_ID, id)
                );

        return classroomMapper.toResponseDTO(classroom);
    }

    // Read by teacher ID
    @Override
    public List<ClassroomResponseDTO> getClassroomsByTeacherId(UUID teacherId) {
        List<Classroom> entities = classroomRepository.findByTeacherId(teacherId);
        return classroomMapper.toResponseDTOList(entities);
    }

    // Read by invite code
    @Override
    public List<ClassroomResponseDTO> getClassroomsByInviteCode(String inviteCode) {
        List<Classroom> entities = classroomRepository.findByInviteCode(inviteCode);
        return classroomMapper.toResponseDTOList(entities);
    }

    // Update
    @Override
    public ClassroomResponseDTO updateClassroom(UUID id, ClassroomRequestDTO classroomRequestDTO) {

        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(ClassroomUtil.RESOURCE_CLASSROOM, ClassroomUtil.FIELD_ID, id)
                );

        // Check if invite code is being changed to an existing one
        if (classroomRequestDTO.getInviteCode() != null &&
                !classroomRequestDTO.getInviteCode().equals(classroom.getInviteCode())) {

            if (!classroomRepository
                    .findByInviteCode(classroomRequestDTO.getInviteCode())
                    .isEmpty()) {

                throw new DuplicateResourceException(
                        ClassroomUtil.RESOURCE_CLASSROOM,
                        ClassroomUtil.FIELD_INVITE_CODE,
                        classroomRequestDTO.getInviteCode()
                );
            }
        }

        classroomMapper.updateEntityFromDTO(classroom, classroomRequestDTO);
        Classroom updatedEntity = classroomRepository.save(classroom);

        return classroomMapper.toResponseDTO(updatedEntity);
    }

    // Delete
    @Override
    public void deleteClassroom(UUID id) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(ClassroomUtil.RESOURCE_CLASSROOM, ClassroomUtil.FIELD_ID, id)
                );

        classroomRepository.delete(classroom);
    }
}
