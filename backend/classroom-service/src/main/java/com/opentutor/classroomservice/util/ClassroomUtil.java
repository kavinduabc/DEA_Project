package com.opentutor.classroomservice.util;

public class ClassroomUtil {

    private ClassroomUtil() {}

    // ── Controller ────────────────────────────────────────────────────────────
    public static final String API_BASE_PATH = "api/classrooms";

    // ── Resource / Field names (used in exceptions) ───────────────────────────
    public static final String RESOURCE_CLASSROOM = "Classroom";
    public static final String RESOURCE_USER      = "User";
    public static final String FIELD_ID           = "id";
    public static final String FIELD_INVITE_CODE  = "inviteCode";

    // ── UserServiceClient ─────────────────────────────────────────────────────
    public static final String PROFILE_SERVICE_USER_URI = "http://profile-service/api/user/view/id/{id}";

    // ── Mapper defaults ───────────────────────────────────────────────────────
    public static final boolean DEFAULT_IS_ACTIVE = true;

    // ── Validation messages (ClassroomRequestDTO) ─────────────────────────────
    public static final String MSG_TEACHER_ID_REQUIRED = "Teacher ID is required";
    public static final String MSG_TITLE_REQUIRED      = "Title is required";
    public static final String MSG_SUBJECT_REQUIRED    = "Subject is required";
    public static final String MSG_TITLE_LENGTH =
            "Title should be between 3 and 100 characters";
    public static final String MSG_SUBJECT_LENGTH =
            "Subject should be between 3 and 50 characters";
    public static final String MSG_INVITE_CODE_LENGTH =
            "Invite code should be between 6 and 10 characters";

    // ── Validation constraints ────────────────────────────────────────────────
    public static final int TITLE_MIN_LENGTH       = 3;
    public static final int TITLE_MAX_LENGTH       = 100;
    public static final int SUBJECT_MIN_LENGTH     = 3;
    public static final int SUBJECT_MAX_LENGTH     = 50;
    public static final int INVITE_CODE_MIN_LENGTH = 6;
    public static final int INVITE_CODE_MAX_LENGTH = 10;

    // ── Validation messages (Classroom entity) ────────────────────────────────
    public static final String MSG_ENTITY_TEACHER_ID_REQUIRED = "Teacher Id is required";
    public static final String MSG_ENTITY_TITLE_REQUIRED      = "Title is required";
    public static final String MSG_ENTITY_SUBJECT_REQUIRED    = "Subject is required";

    // ── GlobalExceptionHandler strings ────────────────────────────────────────
    public static final String ERROR_CONFLICT               = "Conflict";
    public static final String ERROR_NOT_FOUND              = "Not Found";
    public static final String ERROR_UNAUTHORIZED           = "Unauthorized";
    public static final String MSG_UNAUTHORIZED             = "Invalid or missing authorization token.";
    public static final String ERROR_VALIDATION_FAILED      = "Validation Failed";
    public static final String ERROR_INTERNAL_SERVER        = "Internal Server Error";
    public static final String MSG_INVALID_INPUT            = "Invalid input data";
    public static final String MSG_UNEXPECTED_ERROR =
            "An unexpected error occurred. Please try again later.";
    public static final String URI_PREFIX                   = "uri=";

    // DataIntegrityViolation detail checks
    public static final String DUPLICATE_KEY                = "duplicate key";
    public static final String CONSTRAINT_EMAIL             = "email";
    public static final String CONSTRAINT_PHONE             = "phone";
    public static final String FOREIGN_KEY_CONSTRAINT       = "foreign key constraint";
    public static final String NOT_NULL_CONSTRAINT          = "not-null constraint";

    // DataIntegrityViolation messages
    public static final String MSG_DATA_INTEGRITY_DEFAULT =
            "Data integrity violation occurred";
    public static final String MSG_DUPLICATE_EMAIL =
            "A profile with this email address already exists. Please use a different email.";
    public static final String MSG_DUPLICATE_PHONE =
            "A profile with this phone number already exists. Please use a different phone number.";
    public static final String MSG_DUPLICATE_GENERIC =
            "A profile with these details already exists. Please use different information.";
    public static final String MSG_FOREIGN_KEY =
            "Cannot perform this operation due to related data constraints.";
    public static final String MSG_NOT_NULL =
            "Required field cannot be empty.";
}
