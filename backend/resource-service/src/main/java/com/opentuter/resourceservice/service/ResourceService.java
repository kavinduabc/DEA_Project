package com.opentuter.resourceservice.service;

import com.opentuter.resourceservice.dto.ResourceRequestDto;
import com.opentuter.resourceservice.dto.ResourceResponseDto;
import com.opentuter.resourceservice.exception.ResourceNotFoundException;
import com.opentuter.resourceservice.mapper.ResourceMapper;
import com.opentuter.resourceservice.model.Module;
import com.opentuter.resourceservice.model.Resource;
import com.opentuter.resourceservice.repository.ModuleRepository;
import com.opentuter.resourceservice.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ModuleRepository moduleRepository;

    public ResourceService(ResourceRepository resourceRepository,
                           ModuleRepository moduleRepository) {
        this.resourceRepository = resourceRepository;
        this.moduleRepository = moduleRepository;
    }

    // CREATE RESOURCE
    public ResourceResponseDto createResource(ResourceRequestDto dto) {

        Module module = moduleRepository.findById(dto.getModuleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Module", "id", dto.getModuleId()));

        Resource resource = ResourceMapper.toEntity(dto);
        resource.setModule(module);

        return ResourceMapper.toDto(resourceRepository.save(resource));
    }

    // GET RESOURCE
    public ResourceResponseDto getResourceById(UUID id) {
        return resourceRepository.findById(id)
                .map(ResourceMapper::toDto)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource", "id", id));
    }

    // UPDATE RESOURCE
    public ResourceResponseDto updateResource(UUID id, ResourceRequestDto dto) {

        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource", "id", id));

        resource.setTitle(dto.getTitle());
        resource.setType(dto.getType());
        resource.setFileUrl(dto.getFileUrl());

        return ResourceMapper.toDto(resourceRepository.save(resource));
    }

    // DELETE RESOURCE
    public void deleteResource(UUID id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource", "id", id));

        resourceRepository.delete(resource);
    }
}
