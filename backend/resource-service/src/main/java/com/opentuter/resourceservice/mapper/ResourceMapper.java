package com.opentuter.resourceservice.mapper;

import com.opentuter.resourceservice.dto.ResourceRequestDto;
import com.opentuter.resourceservice.dto.ResourceResponseDto;
import com.opentuter.resourceservice.model.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResourceMapper {

    // Convert ResourceRequestDto to Resource entity
    public Resource toEntity(ResourceRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Resource entity = new Resource();
        entity.setModuleId(dto.getModuleId());
        entity.setTitle(dto.getTitle());
        entity.setType(dto.getType());
        entity.setFileUrl(dto.getFileUrl());

        return entity;
    }

    // Convert Resource entity to ResourceResponseDto
    public ResourceResponseDto toResponseDto(Resource entity) {
        if (entity == null) {
            return null;
        }

        ResourceResponseDto dto = new ResourceResponseDto();
        dto.setId(entity.getId());
        dto.setModuleId(entity.getModuleId());
        dto.setTitle(entity.getTitle());
        dto.setType(entity.getType());
        dto.setFileUrl(entity.getFileUrl());

        return dto;
    }

    // Convert list of Resource entities to list of ResourceResponseDto
    public List<ResourceResponseDto> toResponseDtoList(List<Resource> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    // Update existing entity with DTO data
    public void updateEntityFromDto(Resource entity, ResourceRequestDto dto) {
        if (entity == null || dto == null) {
            return;
        }

        entity.setModuleId(dto.getModuleId());
        entity.setTitle(dto.getTitle());
        entity.setType(dto.getType());
        entity.setFileUrl(dto.getFileUrl());
    }
}
