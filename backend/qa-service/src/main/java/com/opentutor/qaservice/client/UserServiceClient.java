package com.opentutor.qaservice.client;

import com.opentutor.qaservice.util.QaUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.UUID;

@Component
public class UserServiceClient {

    private final WebClient webClient;

    public UserServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public boolean userExists(UUID userId) {
        try {
            webClient.get()
                    .uri(QaUtil.PROFILE_SERVICE_GET_USER_BY_ID, userId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return true;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error checking user existence: " + e.getMessage(), e);
        }
    }
}
