package com.opentuter.resourceservice.controller;

import com.opentuter.resourceservice.dto.ModuleRequestDto;
import com.opentuter.resourceservice.dto.ModuleResponseDto;
import com.opentuter.resourceservice.dto.ResourceRequestDto;
import com.opentuter.resourceservice.dto.ResourceResponseDto;
import com.opentuter.resourceservice.service.ResourceService;
import com.opentuter.resourceservice.util.RscourceUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(RscourceUtil.RESOURCE_BASE_PATH)
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    // POST /api/modules
    @PostMapping(RscourceUtil.MODULES_PATH)
    public ResponseEntity<ModuleResponseDto> createModule(
            @Valid @RequestBody ModuleRequestDto request) {

        return new ResponseEntity<>(
                resourceService.createModule(request),
                HttpStatus.CREATED
        );
    }

    // GET /api/modules/{id}
    @GetMapping(RscourceUtil.MODULES_BY_ID_PATH)
    public ResponseEntity<ModuleResponseDto> getModuleById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                resourceService.getModuleById(id)
        );
    }

    // GET /api/modules
    @GetMapping(RscourceUtil.MODULES_PATH)
    public ResponseEntity<List<ModuleResponseDto>> getAllModules() {

        return ResponseEntity.ok(
                resourceService.getAllModules()
        );
    }

    // PUT /api/modules/{id}
    @PutMapping(RscourceUtil.MODULES_BY_ID_PATH)
    public ResponseEntity<ModuleResponseDto> updateModule(
            @PathVariable UUID id,
            @Valid @RequestBody ModuleRequestDto request) {

        return ResponseEntity.ok(
                resourceService.updateModule(id, request)
        );
    }

    // DELETE /api/modules/{id}
    @DeleteMapping(RscourceUtil.MODULES_BY_ID_PATH)
    public ResponseEntity<Void> deleteModule(@PathVariable UUID id) {

        resourceService.deleteModule(id);
        return ResponseEntity.noContent().build();
    }

    // POST /api/resources
    @PostMapping(RscourceUtil.RESOURCE_CREATE_PATH)
    public ResponseEntity<ResourceResponseDto> createResource(
            @Valid @RequestBody ResourceRequestDto request) {

        return new ResponseEntity<>(
                resourceService.createResource(request),
                HttpStatus.CREATED
        );
    }

    // GET /api/resources/{id}
    @GetMapping(RscourceUtil.RESOURCE_BY_ID_PATH)
    public ResponseEntity<ResourceResponseDto> getResource(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                resourceService.getResourceById(id)
        );
    }

    // GET /api/resources/module/{moduleId}
    @GetMapping(RscourceUtil.RESOURCES_BY_MODULE_PATH)
    public ResponseEntity<List<ResourceResponseDto>> getResourcesByModuleId(
            @PathVariable UUID moduleId) {

        return ResponseEntity.ok(
                resourceService.getResourcesByModuleId(moduleId)
        );
    }

    // PUT /api/resources/{id}
    @PutMapping(RscourceUtil.RESOURCE_BY_ID_PATH)
    public ResponseEntity<ResourceResponseDto> updateResource(
            @PathVariable UUID id,
            @Valid @RequestBody ResourceRequestDto request) {

        return ResponseEntity.ok(
                resourceService.updateResource(id, request)
        );
    }

    // DELETE /api/resources/{id}
    @DeleteMapping(RscourceUtil.RESOURCE_BY_ID_PATH)
    public ResponseEntity<Void> deleteResource(@PathVariable UUID id) {

        resourceService.deleteResource(id);
        return ResponseEntity.noContent().build();
    }
}