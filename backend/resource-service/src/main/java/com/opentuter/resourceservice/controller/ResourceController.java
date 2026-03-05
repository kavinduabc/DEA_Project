package com.opentuter.resourceservice.controller;

import com.opentuter.resourceservice.dto.ModuleRequestDto;
import com.opentuter.resourceservice.dto.ModuleResponseDto;
import com.opentuter.resourceservice.dto.ResourceRequestDto;
import com.opentuter.resourceservice.dto.ResourceResponseDto;
import com.opentuter.resourceservice.service.ResourceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    // ==================================================
    // MODULE ENDPOINTS
    // ==================================================

    // ✅ CREATE MODULE
    // POST /api/modules
    @PostMapping("/modules")
    public ResponseEntity<ModuleResponseDto> createModule(
            @Valid @RequestBody ModuleRequestDto request) {

        return new ResponseEntity<>(
                resourceService.createModule(request),
                HttpStatus.CREATED
        );
    }

    // ✅ GET MODULE BY ID
    // GET /api/modules/{id}
    @GetMapping("/modules/{id}")
    public ResponseEntity<ModuleResponseDto> getModuleById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                resourceService.getModuleById(id)
        );
    }

    // ✅ GET ALL MODULES
    // GET /api/modules
    @GetMapping("/modules")
    public ResponseEntity<List<ModuleResponseDto>> getAllModules() {

        return ResponseEntity.ok(
                resourceService.getAllModules()
        );
    }

    // ✅ UPDATE MODULE
    // PUT /api/modules/{id}
    @PutMapping("/modules/{id}")
    public ResponseEntity<ModuleResponseDto> updateModule(
            @PathVariable UUID id,
            @Valid @RequestBody ModuleRequestDto request) {

        return ResponseEntity.ok(
                resourceService.updateModule(id, request)
        );
    }

    // ✅ DELETE MODULE
    // DELETE /api/modules/{id}
    @DeleteMapping("/modules/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable UUID id) {

        resourceService.deleteModule(id);
        return ResponseEntity.noContent().build();
    }

    // ==================================================
    // RESOURCE ENDPOINTS
    // ==================================================

    // ✅ CREATE RESOURCE
    // POST /api/resources
    @PostMapping("/resources")
    public ResponseEntity<ResourceResponseDto> createResource(
            @Valid @RequestBody ResourceRequestDto request) {

        return new ResponseEntity<>(
                resourceService.createResource(request),
                HttpStatus.CREATED
        );
    }

    // ✅ GET RESOURCE BY ID
    // GET /api/resources/{id}
    @GetMapping("/resources/{id}")
    public ResponseEntity<ResourceResponseDto> getResource(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                resourceService.getResourceById(id)
        );
    }

    // ✅ GET RESOURCES BY MODULE ID
    // GET /api/resources/module/{moduleId}
    @GetMapping("/resources/module/{moduleId}")
    public ResponseEntity<List<ResourceResponseDto>> getResourcesByModuleId(
            @PathVariable UUID moduleId) {

        return ResponseEntity.ok(
                resourceService.getResourcesByModuleId(moduleId)
        );
    }

    // ✅ UPDATE RESOURCE
    // PUT /api/resources/{id}
    @PutMapping("/resources/{id}")
    public ResponseEntity<ResourceResponseDto> updateResource(
            @PathVariable UUID id,
            @Valid @RequestBody ResourceRequestDto request) {

        return ResponseEntity.ok(
                resourceService.updateResource(id, request)
        );
    }

    // ✅ DELETE RESOURCE
    // DELETE /api/resources/{id}
    @DeleteMapping("/resources/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable UUID id) {

        resourceService.deleteResource(id);
        return ResponseEntity.noContent().build();
    }
}