package com.opentuter.resourceservice.exception;

import com.opentuter.resourceservice.util.RscourceUtil;

import java.util.UUID;

public class ClassroomNotFoundException extends RuntimeException {

    public ClassroomNotFoundException(UUID classroomId) {
        super(RscourceUtil.CLASSROOM_NOT_FOUND_PREFIX + classroomId);
    }
}

