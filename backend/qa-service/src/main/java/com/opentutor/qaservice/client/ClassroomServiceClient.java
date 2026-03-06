package com.opentutor.qaservice.client;

import com.opentutor.qaservice.util.QaUtil;
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
                    .uri(QaUtil.CLASSROOM_SERVICE_GET_BY_ID, classroomId)
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
