package com.opentutor.classroom_service.service;

import com.opentutor.classroom_service.dto.ClassroomRequestDTO;
import com.opentutor.classroom_service.dto.ClassroomResponseDTO;
import com.opentutor.classroom_service.exception.DuplicateResourceException;
import com.opentutor.classroom_service.exception.ResourceNotFoundException;
import com.opentutor.classroom_service.mapper.ClassroomMapper;
import com.opentutor.classroom_service.model.Classroom;
import com.opentutor.classroom_service.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;

    public ClassroomService(ClassroomRepository classroomRepository,
                            ClassroomMapper classroomMapper) {
        this.classroomRepository = classroomRepository;
        this.classroomMapper = classroomMapper;
    }

    // Create
    public ClassroomResponseDTO createClassroom(ClassroomRequestDTO classroomRequestDTO) {

        // Check if invite code already exists (if provided)
        if (classroomRequestDTO.getInviteCode() != null &&
                !classroomRepository.findByInviteCode(classroomRequestDTO.getInviteCode()).isEmpty()) {

            throw new DuplicateResourceException(
                    "Classroom",
                    "inviteCode",
                    classroomRequestDTO.getInviteCode()
            );
        }

        Classroom entity = classroomMapper.toEntity(classroomRequestDTO);
        Classroom savedEntity = classroomRepository.save(entity);

        return classroomMapper.toResponseDTO(savedEntity);
    }

    // Read all
    public List<ClassroomResponseDTO> getAllClassrooms() {
        List<Classroom> entities = classroomRepository.findAll();
        return classroomMapper.toResponseDTOList(entities);
    }

    // Read by ID
    public ClassroomResponseDTO getClassroomById(Long id) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Classroom", "id", id)
                );

        return classroomMapper.toResponseDTO(classroom);
    }

    // Read by teacher ID
    public List<ClassroomResponseDTO> getClassroomsByTeacherId(UUID teacherId) {
        List<Classroom> entities = classroomRepository.findByTeacherId(teacherId);
        return classroomMapper.toResponseDTOList(entities);
    }

    // Read by invite code
    public List<ClassroomResponseDTO> getClassroomsByInviteCode(String inviteCode) {
        List<Classroom> entities = classroomRepository.findByInviteCode(inviteCode);
        return classroomMapper.toResponseDTOList(entities);
    }

    // Update
    public ClassroomResponseDTO updateClassroom(Long id,
                                                ClassroomRequestDTO classroomRequestDTO) {

        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Classroom", "id", id)
                );

        // Check if invite code is being changed to an existing one
        if (classroomRequestDTO.getInviteCode() != null &&
                !classroomRequestDTO.getInviteCode().equals(classroom.getInviteCode())) {

            if (!classroomRepository
                    .findByInviteCode(classroomRequestDTO.getInviteCode())
                    .isEmpty()) {

                throw new DuplicateResourceException(
                        "Classroom",
                        "inviteCode",
                        classroomRequestDTO.getInviteCode()
                );
            }
        }

        classroomMapper.updateEntityFromDTO(classroom, classroomRequestDTO);
        Classroom updatedEntity = classroomRepository.save(classroom);

        return classroomMapper.toResponseDTO(updatedEntity);
    }

    // Delete
    public void deleteClassroom(Long id) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Classroom", "id", id)
                );

        classroomRepository.delete(classroom);
    }

}
