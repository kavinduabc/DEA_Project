package com.opentuter.resourceservice.exception;

import java.util.UUID;

public class ClassroomNotFoundException extends RuntimeException {

    public ClassroomNotFoundException(UUID classroomId) {
        super("Classroom not found with id: " + classroomId);
    }
}

