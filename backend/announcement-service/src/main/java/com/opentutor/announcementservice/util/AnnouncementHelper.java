package com.opentutor.announcementservice.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Helper/utility class for the Announcement Service.
 * Contains reusable static helper methods to keep service and mapper classes clean.
 */
public final class AnnouncementHelper {

    // Prevent instantiation
    private AnnouncementHelper() {}

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern(AnnouncementConstants.DATE_TIME_FORMAT);

    /**
     * Generates a unique, random share token for public announcement sharing.
     *
     * @return a randomly generated UUID string as a share token
     */
    public static String generateShareToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * Formats a {@link LocalDateTime} to a human-readable string using the
     * standard format defined in {@link AnnouncementConstants#DATE_TIME_FORMAT}.
     *
     * @param dateTime the date-time to format; returns null if input is null
     * @return the formatted date-time string, or null if input is null
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.format(FORMATTER);
    }

    /**
     * Checks whether a given string is null or blank (empty / whitespace-only).
     *
     * @param value the string to check
     * @return true if the string is null or blank, false otherwise
     */
    public static boolean isNullOrBlank(String value) {
        return value == null || value.isBlank();
    }

    /**
     * Safely converts a string to a {@link UUID}.
     * Returns null if the string is invalid or null instead of throwing an exception.
     *
     * @param uuidStr the UUID string to parse
     * @return the parsed UUID, or null if invalid
     */
    public static UUID parseUUID(String uuidStr) {
        if (isNullOrBlank(uuidStr)) return null;
        try {
            return UUID.fromString(uuidStr);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
