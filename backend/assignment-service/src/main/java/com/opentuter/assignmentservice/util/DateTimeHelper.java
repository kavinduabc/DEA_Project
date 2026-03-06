package com.opentuter.assignmentservice.util;

import java.time.LocalDateTime;


public final class DateTimeHelper {

    // Private constructor prevents instantiation of this utility class.
    private DateTimeHelper() {
        throw new UnsupportedOperationException("DateTimeHelper is a utility class and cannot be instantiated.");
    }

    public static boolean isPastDue(LocalDateTime dueDate) {
        return dueDate != null && dueDate.isBefore(LocalDateTime.now());
    }
}
