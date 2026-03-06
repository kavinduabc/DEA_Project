package com.opentutor.announcementservice.client;

import com.opentutor.announcementservice.util.AnnouncementConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.UUID;

@Component
public class ClassroomSeviceClient {

    private final WebClient.Builder webClientBuilder;

    public ClassroomSeviceClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public boolean classroomExists(UUID classroomId) {
        try {
            webClientBuilder.build()
                    .get()
                    .uri(AnnouncementConstants.CLASSROOM_SERVICE_URL, classroomId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return true;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        }
    }
}
