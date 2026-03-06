package com.opentuter.resourceservice.client;

import com.opentuter.resourceservice.exception.ClassroomNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.UUID;

@Component
public class ClassroomServiceClient {

    private final WebClient webClient;

    public ClassroomServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://classroom-service")
                .build();
    }

    public void validateClassroom(UUID classroomId) {
        try {
            webClient.get()
                    .uri("/api/classrooms/{id}", classroomId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            throw new ClassroomNotFoundException(classroomId);
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Error while validating classroom with id: " + classroomId, e);
        }
    }
}
