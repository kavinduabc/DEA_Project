package com.opentuter.resourceservice.service.impl;

import com.opentuter.resourceservice.client.ClassroomServiceClient;
import com.opentuter.resourceservice.dto.*;
import com.opentuter.resourceservice.exception.ResourceNotFoundException;
import com.opentuter.resourceservice.mapper.ResourceMapper;
import com.opentuter.resourceservice.model.Module;
import com.opentuter.resourceservice.model.Resource;
import com.opentuter.resourceservice.repository.ModuleRepository;
import com.opentuter.resourceservice.repository.ResourceRepository;
import com.opentuter.resourceservice.service.ResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final ModuleRepository moduleRepository;
    private final ClassroomServiceClient classroomServiceClient;

    public ResourceServiceImpl(ResourceRepository resourceRepository,
                               ModuleRepository moduleRepository,
                               ClassroomServiceClient classroomServiceClient) {
        this.resourceRepository = resourceRepository;
        this.moduleRepository = moduleRepository;
        this.classroomServiceClient = classroomServiceClient;
    }

    // ================= MODULE =================

    @Override
    public ModuleResponseDto createModule(ModuleRequestDto dto) {
        // Validate that the classroom exists before creating the module
        classroomServiceClient.validateClassroom(dto.getClassroomId());

        Module module = new Module();
        module.setTitle(dto.getTitle());
        module.setClassroomId(dto.getClassroomId());

        Module saved = moduleRepository.save(module);

        return new ModuleResponseDto(
                saved.getId(),
                saved.getTitle(),
                saved.getClassroomId()
        );
    }

    @Override
    public ModuleResponseDto updateModule(UUID id, ModuleRequestDto dto) {
        Module existing = moduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Module", "id", id));

        existing.setTitle(dto.getTitle());
        existing.setClassroomId(dto.getClassroomId());

        Module updated = moduleRepository.save(existing);

        return new ModuleResponseDto(
                updated.getId(),
                updated.getTitle(),
                updated.getClassroomId()
        );
    }

    @Override
    public void deleteModule(UUID id) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Module", "id", id));

        moduleRepository.delete(module);
    }

    @Override
    public List<ModuleResponseDto> getAllModules() {
        return moduleRepository.findAll()
                .stream()
                .map(m -> new ModuleResponseDto(
                        m.getId(),
                        m.getTitle(),
                        m.getClassroomId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ModuleResponseDto getModuleById(UUID id) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Module", "id", id));

        return new ModuleResponseDto(
                module.getId(),
                module.getTitle(),
                module.getClassroomId()
        );
    }

    // ================= RESOURCE =================

    @Override
    public ResourceResponseDto createResource(ResourceRequestDto dto) {
        Module module = moduleRepository.findById(dto.getModuleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Module", "id", dto.getModuleId()));

        Resource resource = ResourceMapper.toEntity(dto);
        resource.setModule(module);

        Resource saved = resourceRepository.save(resource);

        return ResourceMapper.toDto(saved);
    }

    @Override
    public ResourceResponseDto getResourceById(UUID id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource", "id", id));

        return ResourceMapper.toDto(resource);
    }

    @Override
    public List<ResourceResponseDto> getResourcesByModuleId(UUID moduleId) {
        moduleRepository.findById(moduleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Module", "id", moduleId));

        return resourceRepository.findByModule_Id(moduleId)
                .stream()
                .map(ResourceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResourceResponseDto updateResource(UUID id, ResourceRequestDto dto) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource", "id", id));

        resource.setTitle(dto.getTitle());
        resource.setType(dto.getType());
        resource.setFileUrl(dto.getFileUrl());

        Resource updated = resourceRepository.save(resource);

        return ResourceMapper.toDto(updated);
    }

    @Override
    public void deleteResource(UUID id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource", "id", id));

        resourceRepository.delete(resource);
    }
}
