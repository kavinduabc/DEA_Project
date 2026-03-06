package com.opentutor.quizservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class ResourceServiceClient {

    private final WebClient webClient;

    public ResourceServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://resource-service")
                .build();
    }

    /**
     * Checks whether a module with the given ID exists in the resource-service.
     *
     * @param moduleId the UUID of the module to validate
     * @return true if the module exists, false if a 404 is returned
     * @throws RuntimeException for any other non-2xx response
     */
    public boolean isModuleValid(String moduleId) {
        try {
            webClient.get()
                    .uri("/api/resources/modules/{id}", moduleId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return true;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        }
    }
}
