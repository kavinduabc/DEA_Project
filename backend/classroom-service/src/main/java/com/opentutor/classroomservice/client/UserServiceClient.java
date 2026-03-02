package com.opentutor.classroomservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.UUID;

@Component
public class UserServiceClient {

    private final WebClient.Builder webClientBuilder;

    public UserServiceClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public boolean userExists(UUID userId) {
        try {
            webClientBuilder.build()
                    .get()
                    .uri("http://profile-service/api/user/view/id/{id}", userId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return true;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        }
    }
}

