package com.opentutor.quizservice.client;

import com.opentutor.quizservice.util.QuizUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class ResourceServiceClient {

    private final WebClient webClient;

    public ResourceServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(QuizUtil.RESOURCE_SERVICE_BASE_URL)
                .build();
    }

    public boolean isModuleValid(String moduleId) {
        try {
            webClient.get()
                    .uri(QuizUtil.RESOURCE_SERVICE_GET_MODULE_BY_ID, moduleId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return true;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        }
    }
}
