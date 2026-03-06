package com.opentutor.qaservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class ClassroomServiceClient {

    private final WebClient webClient;

    public ClassroomServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public boolean classroomExists(String classroomId) {
        try {
            webClient.get()
                    .uri("http://classroom-service/api/classrooms/{id}", classroomId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return true;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error checking classroom existence: " + e.getMessage(), e);
        }
    }
}
