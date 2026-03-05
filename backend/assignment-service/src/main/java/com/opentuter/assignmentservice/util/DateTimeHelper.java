package com.opentuter.assignmentservice.util;

import java.time.LocalDateTime;

/**
 * Utility class providing helper methods for date and time operations
 * used across the Assignment Service.
 */
public final class DateTimeHelper {

    // Private constructor prevents instantiation of this utility class.
    private DateTimeHelper() {
        throw new UnsupportedOperationException("DateTimeHelper is a utility class and cannot be instantiated.");
    }

    /**
     * Checks whether the given due date has already passed relative to the current
     * system time.
     *
     * @param dueDate the due date to evaluate; may be {@code null}
     * @return {@code true} if {@code dueDate} is non-null and is before the current
     *         date-time,
     *         {@code false} if {@code dueDate} is {@code null} (no deadline set) or
     *         is still in the future
     */
    public static boolean isPastDue(LocalDateTime dueDate) {
        return dueDate != null && dueDate.isBefore(LocalDateTime.now());
    }
}
