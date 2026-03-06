package com.opentutor.announcementservice.util;

/**
 * Central constants class for the Announcement Service.
 * Avoids hardcoded "magic strings" scattered across the codebase.
 */
public final class AnnouncementConstants {

    // Prevent instantiation
    private AnnouncementConstants() {}

    // ── Base URL ──────────────────────────────────────────────────────────────
    public static final String BASE_URL          = "/api/announcements";
    public static final String CLASSROOM_URL     = "/classroom/{classroomId}";
    public static final String SHARE_URL         = "/{id}/share";
    public static final String SHARED_TOKEN_URL  = "/share/{token}";

    public static final String CLASSROOM_SERVICE_URL = "http://classroom-service/api/classrooms/{id}";
    public static final String PROFILE_SERVICE_URL = "http://profile-service/api/user/view/id/{id}";

    // ── Resource Names (used in exceptions) ───────────────────────────────────
    public static final String RESOURCE_ANNOUNCEMENT = "Announcement";

    // ── Field Names ───────────────────────────────────────────────────────────
    public static final String FIELD_ID           = "id";
    public static final String FIELD_SHARE_TOKEN  = "shareToken";
    public static final String FIELD_CLASSROOM_ID = "classroomId";
    public static final String FIELD_TEACHER_ID   = "teacherId";

    // ── Date/Time ─────────────────────────────────────────────────────────────
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
}
