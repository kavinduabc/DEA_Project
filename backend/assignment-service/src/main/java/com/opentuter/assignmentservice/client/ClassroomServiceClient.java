package com.opentuter.assignmentservice.client;

import com.opentuter.assignmentservice.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClassroomServiceClient {

    private final WebClient.Builder webClientBuilder;

    /**
     * Verifies that the classroom with the given id exists in the classroom-service.
     * Throws {@link ResourceNotFoundException} if the classroom is not found.
     */
    public void validateClassroomExists(UUID classroomId) {
        try {
            webClientBuilder.build()
                    .get()
                    .uri("http://classroom-service/api/classrooms/{id}", classroomId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            throw new ResourceNotFoundException("Classroom not found with id: " + classroomId);
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Failed to verify classroom (status " + e.getStatusCode() + "): " + e.getMessage());
        }
    }
}
