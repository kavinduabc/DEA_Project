package com.opentutor.classroomservice.service;

import com.opentutor.classroomservice.dto.ClassroomRequestDTO;
import com.opentutor.classroomservice.dto.ClassroomResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ClassroomService {

    ClassroomResponseDTO createClassroom(ClassroomRequestDTO classroomRequestDTO);
    List<ClassroomResponseDTO> getAllClassrooms();
    ClassroomResponseDTO getClassroomById(Long id);
    List<ClassroomResponseDTO> getClassroomsByTeacherId(UUID teacherId);
    List<ClassroomResponseDTO> getClassroomsByInviteCode(String inviteCode);
    ClassroomResponseDTO updateClassroom(Long id, ClassroomRequestDTO classroomRequestDTO);
    void deleteClassroom(Long id);
}
