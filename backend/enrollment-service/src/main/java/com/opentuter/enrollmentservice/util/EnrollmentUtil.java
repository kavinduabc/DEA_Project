package com.opentuter.enrollmentservice.util;

public final class EnrollmentUtil {

    // Prevent instantiation of this utility class
    private EnrollmentUtil() {
        throw new UnsupportedOperationException("EnrollmentUtil is a utility class and cannot be instantiated.");
    }

    /** Base URL for enrollment endpoints */
    public static final String BASE_URL = "/api/enrollments";
    public static final String STUDENT_ENDPOINT = "/student/{studentId}";
    public static final String CLASSROOM_ENDPOINT = "/classroom/{classroomId}";
    public static final String ID_ENDPOINT = "/{id}";


    /** Resource name for Enrollment entity */
    public static final String RESOURCE_ENROLLMENT = "Enrollment";

    /** Resource name for User entity */
    public static final String RESOURCE_USER = "User";

    /** Resource name for Classroom entity */
    public static final String RESOURCE_CLASSROOM = "Classroom";

    /** Field name for ID */
    public static final String FIELD_ID = "id";

    /** Field name for student ID */
    public static final String FIELD_STUDENT_ID = "studentId";

    /** Field name for classroom ID */
    public static final String FIELD_CLASSROOM_ID = "classroomId";

    /** Combined field name for composite key */
    public static final String FIELD_STUDENT_CLASSROOM = "studentId + classroomId";

    /** Error message when required fields are null */
    public static final String ERROR_NULL_FIELDS = "studentId and classroomId must not be null";

    /** Error message for validation failure */
    public static final String ERROR_VALIDATION_FAILED = "Validation Failed";

    /** Error message for invalid input data */
    public static final String ERROR_INVALID_INPUT = "Invalid input data";

    /** Error message for conflict */
    public static final String ERROR_CONFLICT = "Conflict";

    /** Error message for not found */
    public static final String ERROR_NOT_FOUND = "Not Found";

    /** Error message for internal server error */
    public static final String ERROR_INTERNAL_SERVER = "Internal Server Error";

    /** Error message for unexpected errors */
    public static final String ERROR_UNEXPECTED = "An unexpected error occurred. Please try again later.";

    /** Error message for duplicate key constraint */
    public static final String ERROR_DUPLICATE_KEY_EMAIL = "A profile with this email address already exists. Please use a different email.";

    /** Error message for duplicate phone constraint */
    public static final String ERROR_DUPLICATE_KEY_PHONE = "A profile with this phone number already exists. Please use a different phone number.";

    /** Error message for generic duplicate key */
    public static final String ERROR_DUPLICATE_KEY = "A profile with these details already exists. Please use different information.";

    /** Error message for foreign key constraint */
    public static final String ERROR_FOREIGN_KEY = "Cannot perform this operation due to related data constraints.";

    /** Error message for not-null constraint */
    public static final String ERROR_NOT_NULL = "Required field cannot be empty.";

    /** Error message for data integrity violation */
    public static final String ERROR_DATA_INTEGRITY = "Data integrity violation occurred";

    /** Validation message for required student ID */
    public static final String VALIDATION_STUDENT_ID_REQUIRED = "studentId is required";

    /** Validation message for required classroom ID */
    public static final String VALIDATION_CLASSROOM_ID_REQUIRED = "classroomId is required";

    /** Swagger API title */
    public static final String SWAGGER_TITLE = "Enrollment Service APIs";

    /** Swagger API version */
    public static final String SWAGGER_VERSION = "1.0";

    /** Swagger API description */
    public static final String SWAGGER_DESCRIPTION = "Enrollment Service API documentation of OpenTuror Learing Platform";

    /** Database table name for enrollments */
    public static final String TABLE_ENROLLMENTS = "enrollments";

    /** Database column name for ID */
    public static final String COLUMN_ID = "id";

    /** Database column name for student ID */
    public static final String COLUMN_STUDENT_ID = "student_id";

    /** Database column name for classroom ID */
    public static final String COLUMN_CLASSROOM_ID = "classroom_id";

    /** Database column name for enrolled at timestamp */
    public static final String COLUMN_ENROLLED_AT = "enrolled_at";

    /** Log message prefix for duplicate resource */
    public static final String LOG_DUPLICATE_RESOURCE = "Duplicate resource: {}";

    /** Log message prefix for resource not found */
    public static final String LOG_RESOURCE_NOT_FOUND = "Resource not found: {}";

    /** Log message for validation failure with error count */
    public static final String LOG_VALIDATION_FAILED = "Validation failed: {} error(s)";

    /** Log message for database constraint violation */
    public static final String LOG_DB_CONSTRAINT_VIOLATION = "Database constraint violation";

    /** Log message for unexpected error */
    public static final String LOG_UNEXPECTED_ERROR = "Unexpected error occurred";

    /** URI prefix to remove from request description */
    public static final String URI_PREFIX = "uri=";

    /** Path separator for composite keys */
    public static final String PATH_SEPARATOR = "/";

    /** Constraint keyword for duplicate key detection */
    public static final String CONSTRAINT_DUPLICATE_KEY = "duplicate key";

    /** Constraint keyword for email detection */
    public static final String CONSTRAINT_EMAIL = "email";

    /** Constraint keyword for phone detection */
    public static final String CONSTRAINT_PHONE = "phone";

    /** Constraint keyword for foreign key detection */
    public static final String CONSTRAINT_FOREIGN_KEY = "foreign key constraint";

    /** Constraint keyword for not-null detection */
    public static final String CONSTRAINT_NOT_NULL = "not-null constraint";
}
