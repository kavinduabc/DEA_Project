package com.opentuter.assignment_service.service;

import com.opentuter.assignment_service.dto.AssignmentRequestDTO;
import com.opentuter.assignment_service.dto.AssignmentResponseDTO;
import com.opentuter.assignment_service.exception.ResourceNotFoundException;
import com.opentuter.assignment_service.mapper.AssignmentMapper;
import com.opentuter.assignment_service.model.Assignment;
import com.opentuter.assignment_service.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;

    public AssignmentResponseDTO createAssignment(AssignmentRequestDTO request) {
        Assignment assignment = assignmentMapper.toEntity(request);
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return assignmentMapper.toDTO(savedAssignment);
    }

    public AssignmentResponseDTO getAssignmentById(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + id));
        return assignmentMapper.toDTO(assignment);
    }

    public List<AssignmentResponseDTO> getAssignmentsByClassroom(Long classroomId) {
        return assignmentRepository.findByClassroomId(classroomId).stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AssignmentResponseDTO updateAssignment(Long id, AssignmentRequestDTO request) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + id));

        assignment.setTitle(request.getTitle());
        assignment.setDescription(request.getDescription());
        assignment.setDueDate(request.getDueDate());
        // Note: typically we don't update ID, TeacherID, ClassroomID easily, but
        // adhering to fields

        Assignment updatedAssignment = assignmentRepository.save(assignment);
        return assignmentMapper.toDTO(updatedAssignment);
    }

    public void deleteAssignment(Long id) {
        if (!assignmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Assignment not found with id: " + id);
        }
        assignmentRepository.deleteById(id);
    }
}
