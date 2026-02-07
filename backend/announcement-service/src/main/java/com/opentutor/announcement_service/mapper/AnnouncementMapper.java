package com.opentutor.announcement_service.mapper;

import com.opentutor.announcement_service.dto.AnnouncementRequestDTO;
import com.opentutor.announcement_service.dto.AnnouncementResponseDTO;
import com.opentutor.announcement_service.model.Announcement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnnouncementMapper {

    // Convert RequestDTO -> Entity
    public Announcement toEntity(AnnouncementRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Announcement entity = new Announcement();
        entity.setTitle(dto.getTitle());
        entity.setMessage(dto.getMessage());
        entity.setClassroomId(dto.getClassroomId());
        entity.setCreatedBy(dto.getCreatedBy());

        return entity;
    }

    // Convert Entity -> ResponseDTO
    public AnnouncementResponseDTO toResponseDTO(Announcement entity) {
        if (entity == null) {
            return null;
        }

        AnnouncementResponseDTO dto = new AnnouncementResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setMessage(entity.getMessage());
        dto.setClassroomId(entity.getClassroomId());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedAt(entity.getCreatedAt() != null ? entity.getCreatedAt().toString() : null);
        return dto;
    }

    // Convert List<Entity> -> List<ResponseDTO>
    public List<AnnouncementResponseDTO> toResponseDTOList(List<Announcement> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Update existing entity
    public void updateEntityFromDTO(Announcement entity, AnnouncementRequestDTO dto) {
        if (entity == null || dto == null) {
            return;
        }

        entity.setTitle(dto.getTitle());
        entity.setMessage(dto.getMessage());
        entity.setClassroomId(dto.getClassroomId());
    }
}
