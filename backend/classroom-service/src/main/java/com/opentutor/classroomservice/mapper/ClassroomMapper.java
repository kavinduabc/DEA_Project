package com.opentutor.classroomservice.mapper;

import com.opentutor.classroomservice.dto.ClassroomRequestDTO;
import com.opentutor.classroomservice.dto.ClassroomResponseDTO;
import com.opentutor.classroomservice.model.Classroom;
import com.opentutor.classroomservice.util.ClassroomUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassroomMapper {

    // Convert ClassroomRequestDTO to ClassroomEntity
    public Classroom toEntity(ClassroomRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Classroom entity = new Classroom();
        entity.setTeacherId(dto.getTeacherId());
        entity.setTitle(dto.getTitle());
        entity.setSubject(dto.getSubject());
        entity.setBannerImage(dto.getBannerImage());
        entity.setInviteCode(dto.getInviteCode());
        entity.setActive(ClassroomUtil.DEFAULT_IS_ACTIVE); // default value

        return entity;
    }

    // Convert ClassroomEntity to ClassroomResponseDTO
    public ClassroomResponseDTO toResponseDTO(Classroom entity) {
        if (entity == null) {
            return null;
        }

        ClassroomResponseDTO dto = new ClassroomResponseDTO();
        dto.setId(entity.getId());
        dto.setTeacherId(entity.getTeacherId());
        dto.setTitle(entity.getTitle());
        dto.setSubject(entity.getSubject());
        dto.setBannerImage(entity.getBannerImage());
        dto.setInviteCode(entity.getInviteCode());
        dto.setActive(entity.getActive());

        return dto;
    }

    // Convert List of ClassroomEntity to List of ClassroomResponseDTO
    public List<ClassroomResponseDTO> toResponseDTOList(List<Classroom> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Update entity with DTO data
    public void updateEntityFromDTO(Classroom entity, ClassroomRequestDTO dto) {
        if (entity == null || dto == null) {
            return;
        }

        entity.setTeacherId(dto.getTeacherId());
        entity.setTitle(dto.getTitle());
        entity.setSubject(dto.getSubject());
        entity.setBannerImage(dto.getBannerImage());
        entity.setInviteCode(dto.getInviteCode());
    }
}
