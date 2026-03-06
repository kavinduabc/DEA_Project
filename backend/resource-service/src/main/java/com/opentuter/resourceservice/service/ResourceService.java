package com.opentuter.resourceservice.service;

import com.opentuter.resourceservice.dto.*;

import java.util.List;
import java.util.UUID;

public interface ResourceService {

    ModuleResponseDto createModule(ModuleRequestDto dto);
    ModuleResponseDto updateModule(UUID id, ModuleRequestDto dto);
    void deleteModule(UUID id);
    List<ModuleResponseDto> getAllModules();
    ModuleResponseDto getModuleById(UUID id);
    ResourceResponseDto createResource(ResourceRequestDto dto);
    ResourceResponseDto getResourceById(UUID id);
    List<ResourceResponseDto> getResourcesByModuleId(UUID moduleId);
    ResourceResponseDto updateResource(UUID id, ResourceRequestDto dto);
    void deleteResource(UUID id);
}