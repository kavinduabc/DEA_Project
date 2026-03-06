package com.opentuter.assignmentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import java.util.UUID;

@Entity
@Table(name = "assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;

    @Column(name = "max_points")
    private Double maxPoints;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "classroom_id")
    private UUID classroomId;
}
