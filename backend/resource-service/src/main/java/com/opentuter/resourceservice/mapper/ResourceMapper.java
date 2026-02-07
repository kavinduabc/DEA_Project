package com.opentuter.resourceservice.mapper;


import com.opentuter.resourceservice.dto.ResourceRequestDto;
import com.opentuter.resourceservice.dto.ResourceResponseDto;
import com.opentuter.resourceservice.model.Resource;

public class ResourceMapper {

    public static Resource toEntity(ResourceRequestDto dto) {
        Resource resource = new Resource();
        resource.setTitle(dto.getTitle());
        resource.setType(dto.getType());
        resource.setFileUrl(dto.getFileUrl());
        return resource;
    }

    public static ResourceResponseDto toDto(Resource resource) {
        return new ResourceResponseDto(
                resource.getId(),
                resource.getModule().getId(),
                resource.getTitle(),
                resource.getType(),
                resource.getFileUrl()
        );
    }
}
