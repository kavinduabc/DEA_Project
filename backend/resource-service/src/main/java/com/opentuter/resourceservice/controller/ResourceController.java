package com.opentuter.resourceservice.controller;


import com.opentuter.resourceservice.dto.ResourceRequestDto;
import com.opentuter.resourceservice.dto.ResourceResponseDto;
import com.opentuter.resourceservice.exception.ResourceNotFoundException;
import com.opentuter.resourceservice.model.Module;
import com.opentuter.resourceservice.service.ResourceService;
import com.opentuter.resourceservice.repository.ModuleRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ResourceController {

    private final ResourceService resourceService;
    private final ModuleRepository moduleRepository;

    public ResourceController(ResourceService resourceService,
                              ModuleRepository moduleRepository) {
        this.resourceService = resourceService;
        this.moduleRepository = moduleRepository;
    }

    // ==================================================
    // MODULE ENDPOINTS
    // ==================================================

    // POST /api/modules - Create a new chapter/week
    @PostMapping("/modules")
    public ResponseEntity<Module> createModule(@Valid @RequestBody Module module) {
        return new ResponseEntity<>(
                moduleRepository.save(module),
                HttpStatus.CREATED
        );
    }

    // PUT /api/modules/{id} - Rename or reorder a module
    @PutMapping("/modules/{id}")
    public ResponseEntity<Module> updateModule(
            @PathVariable UUID id,
            @RequestBody Module updatedModule) {

        Module module = moduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Module", "id", id));

        module.setTitle(updatedModule.getTitle());

        return ResponseEntity.ok(moduleRepository.save(module));
    }

    // DELETE /api/modules/{id} - Remove a module
    @DeleteMapping("/modules/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable UUID id) {

        Module module = moduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Module", "id", id));

        moduleRepository.delete(module);
        return ResponseEntity.noContent().build();
    }

    // ==================================================
    // RESOURCE ENDPOINTS
    // ==================================================

    // POST /api/resources - Upload a file or add a video link
    @PostMapping("/resources")
    public ResponseEntity<ResourceResponseDto> createResource(
            @Valid @RequestBody ResourceRequestDto request) {

        return new ResponseEntity<>(
                resourceService.createResource(request),
                HttpStatus.CREATED
        );
    }

    // GET /api/resources/{id} - Get resource details
    @GetMapping("/resources/{id}")
    public ResponseEntity<ResourceResponseDto> getResource(@PathVariable UUID id) {
        return ResponseEntity.ok(resourceService.getResourceById(id));
    }

    // PUT /api/resources/{id} - Update resource metadata
    @PutMapping("/resources/{id}")
    public ResponseEntity<ResourceResponseDto> updateResource(
            @PathVariable UUID id,
            @Valid @RequestBody ResourceRequestDto request) {

        return ResponseEntity.ok(
                resourceService.updateResource(id, request)
        );
    }

    // DELETE /api/resources/{id} - Remove a resource
    @DeleteMapping("/resources/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable UUID id) {
        resourceService.deleteResource(id);
        return ResponseEntity.noContent().build();
    }
}
