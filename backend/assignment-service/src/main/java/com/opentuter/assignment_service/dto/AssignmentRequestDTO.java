package com.opentuter.assignment_service.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AssignmentRequestDTO {
    private String title;
    private String description;
    private UUID classroomId;
    private UUID teacherId;
    private Double maxPoints;
    private LocalDateTime dueDate;
}
