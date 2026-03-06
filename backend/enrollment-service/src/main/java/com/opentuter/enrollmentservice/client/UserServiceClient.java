package com.opentuter.enrollmentservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.UUID;

@Component
public class UserServiceClient {

    private final WebClient webClient;
    private static final String PROFILE_SERVICE_USER_URI = "http://profile-service/api/user/view/id/{id}";

    public UserServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    /**
     * Check if a user exists in the profile service
     * @param userId the UUID of the user to check
     * @return true if the user exists, false otherwise
     */
    public boolean userExists(UUID userId) {
        try {
            webClient.get()
                    .uri(PROFILE_SERVICE_USER_URI, userId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return true;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        } catch (Exception e) {
            System.err.println("Error checking user existence: " + e.getMessage());
            return false;
        }
    }
}
