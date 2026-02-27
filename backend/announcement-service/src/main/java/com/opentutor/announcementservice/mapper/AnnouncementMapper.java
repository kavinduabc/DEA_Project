package com.opentutor.announcementservice.mapper;

import com.opentutor.announcementservice.dto.AnnouncementRequestDTO;
import com.opentutor.announcementservice.dto.AnnouncementResponseDTO;
import com.opentutor.announcementservice.model.Announcement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnnouncementMapper {

    public Announcement toEntity(AnnouncementRequestDTO dto) {
        if (dto == null) return null;

        Announcement entity = new Announcement();
        entity.setTitle(dto.getTitle());
        entity.setMessage(dto.getMessage());
        entity.setClassroomId(dto.getClassroomId());
        entity.setTeacherId(dto.getTeacherId());

        return entity;
    }

    public AnnouncementResponseDTO toResponseDTO(Announcement entity) {
        if (entity == null) return null;

        AnnouncementResponseDTO dto = new AnnouncementResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setMessage(entity.getMessage());
        dto.setClassroomId(entity.getClassroomId());
        dto.setTeacherId(entity.getTeacherId());
        dto.setShareToken(entity.getShareToken());
        dto.setCreatedAt(entity.getCreatedAt() != null ? entity.getCreatedAt().toString() : null);

        return dto;
    }

    public List<AnnouncementResponseDTO> toResponseDTOList(List<Announcement> entities) {
        if (entities == null) return null;

        return entities.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void updateEntityFromDTO(Announcement entity, AnnouncementRequestDTO dto) {
        if (entity == null || dto == null) return;

        entity.setTitle(dto.getTitle());
        entity.setMessage(dto.getMessage());
        entity.setClassroomId(dto.getClassroomId());
        entity.setTeacherId(dto.getTeacherId());
    }
}
