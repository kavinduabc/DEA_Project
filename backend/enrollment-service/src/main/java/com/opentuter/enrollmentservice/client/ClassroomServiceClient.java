package com.opentuter.enrollmentservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.UUID;

@Component
public class ClassroomServiceClient {

    private final WebClient webClient;
    private static final String CLASSROOM_SERVICE_URI = "http://classroom-service/api/classrooms/{id}";

    public ClassroomServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    /**
     * Check if a classroom exists in the classroom service
     * @param classroomId the UUID of the classroom to check
     * @return true if the classroom exists, false otherwise
     */
    public boolean classroomExists(int classroomId) {
        try {
            webClient.get()
                    .uri(CLASSROOM_SERVICE_URI, classroomId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return true;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        } catch (Exception e) {
            System.err.println("Error checking classroom existence: " + e.getMessage());
            return false;
        }
    }
}
