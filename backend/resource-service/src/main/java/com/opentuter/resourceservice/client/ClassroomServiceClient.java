package com.opentuter.resourceservice.client;

import com.opentuter.resourceservice.exception.ClassroomNotFoundException;
import com.opentuter.resourceservice.util.RscourceUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.UUID;

@Component
public class ClassroomServiceClient {

    private final WebClient webClient;

    public ClassroomServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(RscourceUtil.CLASSROOM_SERVICE_BASE_URL)
                .build();
    }

    public void validateClassroom(UUID classroomId) {
        try {
            webClient.get()
                    .uri(RscourceUtil.CLASSROOM_SERVICE_GET_BY_ID, classroomId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            throw new ClassroomNotFoundException(classroomId);
        } catch (WebClientResponseException e) {
            throw new RuntimeException(RscourceUtil.CLASSROOM_VALIDATION_ERROR_PREFIX + classroomId, e);
        }
    }
}
