package com.opentuter.assignment_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AssignmentRequestDTO {
    private String title;
    private String description;
    private Long classroomId;
    private Long teacherId;
    private LocalDateTime dueDate;
}
