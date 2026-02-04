package com.opentuter.resourceservice.service;


import com.opentuter.resourceservice.dto.ResourceRequestDto;
import com.opentuter.resourceservice.dto.ResourceResponseDto;
import com.opentuter.resourceservice.exception.DuplicateResourceException;
import com.opentuter.resourceservice.exception.ResourceNotFoundException;
import com.opentuter.resourceservice.mapper.ResourceMapper;
import com.opentuter.resourceservice.model.Resource;
import com.opentuter.resourceservice.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    public ResourceService(ResourceRepository resourceRepository,
                           ResourceMapper resourceMapper) {
        this.resourceRepository = resourceRepository;
        this.resourceMapper = resourceMapper;
    }

    // Create
    public ResourceResponseDto createResource(ResourceRequestDto resourceRequestDto) {

        // Optional duplicate check (same title in same module)
        List<Resource> existingResources =
                resourceRepository.findByModuleId(resourceRequestDto.getModuleId());

        boolean duplicateExists = existingResources.stream()
                .anyMatch(r -> r.getTitle().equalsIgnoreCase(resourceRequestDto.getTitle()));

        if (duplicateExists) {
            throw new DuplicateResourceException(
                    "Resource",
                    "title",
                    resourceRequestDto.getTitle()
            );
        }

        Resource entity = resourceMapper.toEntity(resourceRequestDto);
        Resource savedEntity = resourceRepository.save(entity);

        return resourceMapper.toResponseDto(savedEntity);
    }

    // Read all resources by module
    public List<ResourceResponseDto> getResourcesByModuleId(UUID moduleId) {
        List<Resource> resources = resourceRepository.findByModuleId(moduleId);
        return resourceMapper.toResponseDtoList(resources);
    }

    // Read by ID
    public Optional<ResourceResponseDto> getResourceById(UUID id) {
        return resourceRepository.findById(id)
                .map(resourceMapper::toResponseDto);
    }

    // Update
    public ResourceResponseDto updateResource(UUID id,
                                              ResourceRequestDto resourceRequestDto) {

        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource", "id", id));

        resourceMapper.updateEntityFromDto(resource, resourceRequestDto);
        Resource updatedEntity = resourceRepository.save(resource);

        return resourceMapper.toResponseDto(updatedEntity);
    }

    // Delete
    public void deleteResource(UUID id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource", "id", id));

        resourceRepository.delete(resource);
    }
}

