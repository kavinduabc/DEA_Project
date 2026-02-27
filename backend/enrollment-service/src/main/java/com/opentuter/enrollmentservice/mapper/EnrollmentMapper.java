//package com.opentuter.enrollment_service.mapper;
//
//import com.opentuter.enrollment_service.dto.EnrollmentRequestDTO;
//import com.opentuter.enrollment_service.dto.EnrollmentResponseDTO;
//import com.opentuter.enrollment_service.model.Enrollment;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class EnrollmentMapper {
//
//    public Enrollment toEntity(EnrollmentRequestDTO dto) {
//        if (dto == null) return null;
//
//        Enrollment entity = new Enrollment();
//        entity.setStudentId(dto.getStudentId());
//        entity.setClassroomId(dto.getClassroomId());
//        return entity;
//    }
//
//    public EnrollmentResponseDTO toResponseDTO(Enrollment entity) {
//        if (entity == null) return null;
//
//        EnrollmentResponseDTO dto = new EnrollmentResponseDTO();
//        dto.setId(entity.getId());
//        dto.setStudentId(entity.getStudentId());
//        dto.setClassroomId(entity.getClassroomId());
//        return dto;
//    }
//
//    public List<EnrollmentResponseDTO> toResponseDTOList(List<Enrollment> entities) {
//        return entities.stream().map(this::toResponseDTO).collect(Collectors.toList());
//    }
//
//    public void updateEntityFromDTO(Enrollment entity, EnrollmentRequestDTO dto) {
//        if (entity == null || dto == null) return;
//        entity.setStudentId(dto.getStudentId());
//        entity.setClassroomId(dto.getClassroomId());
//    }
//}

package com.opentuter.enrollmentservice.mapper;

import com.opentuter.enrollmentservice.dto.EnrollmentRequestDTO;
import com.opentuter.enrollmentservice.dto.EnrollmentResponseDTO;
import com.opentuter.enrollmentservice.model.Enrollment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnrollmentMapper {

    public Enrollment toEntity(EnrollmentRequestDTO dto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(dto.getStudentId());
        enrollment.setClassroomId(dto.getClassroomId());
        return enrollment;
    }

    public EnrollmentResponseDTO toResponseDTO(Enrollment entity) {
        EnrollmentResponseDTO dto = new EnrollmentResponseDTO();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudentId());
        dto.setClassroomId(entity.getClassroomId());
        return dto;
    }

    public List<EnrollmentResponseDTO> toResponseDTOList(List<Enrollment> entities) {
        return entities.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
