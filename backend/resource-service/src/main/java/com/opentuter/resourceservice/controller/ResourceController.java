package com.opentuter.resourceservice.controller;

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
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    // Add a new resource (PDF / VIDEO / LINK)
    @PostMapping
    public ResponseEntity<ResourceResponseDto> createResource(
            @Valid @RequestBody ResourceRequestDto resourceRequest) {

        ResourceResponseDto createdResource = resourceService.createResource(resourceRequest);
        return new ResponseEntity<>(createdResource, HttpStatus.CREATED);
    }

    // Get all resources for a module
    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<ResourceResponseDto>> getResourcesByModule(
            @PathVariable UUID moduleId) {

        List<ResourceResponseDto> resources = resourceService.getResourcesByModuleId(moduleId);
        return ResponseEntity.ok(resources);
    }

    // Get resource by ID
    @GetMapping("/{id}")
    public ResponseEntity<ResourceResponseDto> getResourceById(@PathVariable UUID id) {
        return resourceService.getResourceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.<ResourceResponseDto>status(HttpStatus.NOT_FOUND).build());
    }

    // Update resource metadata (title, type, url)
    @PutMapping("/{id}")
    public ResponseEntity<ResourceResponseDto> updateResource(
            @PathVariable UUID id,
            @Valid @RequestBody ResourceRequestDto resourceRequest) {

        try {
            ResourceResponseDto updatedResource = resourceService.updateResource(id, resourceRequest);
            return ResponseEntity.ok(updatedResource);
        } catch (RuntimeException e) {
            return ResponseEntity.<ResourceResponseDto>status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete a resource
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable UUID id) {
        try {
            resourceService.deleteResource(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.<Void>status(HttpStatus.NOT_FOUND).build();
        }
    }
}
