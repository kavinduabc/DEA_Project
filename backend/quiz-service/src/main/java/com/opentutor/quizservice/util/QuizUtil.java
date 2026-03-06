package com.opentutor.quizservice.util;

public class QuizUtil {

    // ── External service URLs ────────────────────────────────────────────────
    public static final String RESOURCE_SERVICE_BASE_URL = "http://resource-service";
    public static final String RESOURCE_SERVICE_GET_MODULE_BY_ID = "/api/resources/modules/{id}";

    // ── Quiz-service base path ───────────────────────────────────────────────
    public static final String QUIZ_BASE_PATH = "/api/quizzes";

    // ── Quiz sub-path constants (relative to QUIZ_BASE_PATH) ────────────────
    public static final String QUIZ_BY_ID_PATH             = "/{id}";
    public static final String QUIZ_BY_MODULE_PATH         = "/module/{moduleId}";

    // ── Question sub-path constants ──────────────────────────────────────────
    public static final String QUESTIONS_BY_QUIZ_PATH      = "/{quizId}/questions";
    public static final String QUESTION_BY_ID_PATH         = "/questions/{id}";

    // ── Attempt sub-path constants ───────────────────────────────────────────
    public static final String ATTEMPTS_BY_QUIZ_PATH       = "/{quizId}/attempts";

    // ── Full endpoint paths (base + sub-path, for documentation/reference) ──
    public static final String QUIZ_CREATE                 = "/api/quizzes";
    public static final String QUIZ_GET_BY_ID              = "/api/quizzes/{id}";
    public static final String QUIZ_GET_BY_MODULE          = "/api/quizzes/module/{moduleId}";
    public static final String QUIZ_UPDATE                 = "/api/quizzes/{id}";
    public static final String QUIZ_DELETE                 = "/api/quizzes/{id}";
    public static final String QUESTION_CREATE             = "/api/quizzes/{quizId}/questions";
    public static final String QUESTION_GET_BY_ID          = "/api/quizzes/questions/{id}";
    public static final String QUESTION_GET_BY_QUIZ        = "/api/quizzes/{quizId}/questions";
    public static final String QUESTION_DELETE             = "/api/quizzes/questions/{id}";
    public static final String ATTEMPT_CREATE              = "/api/quizzes/{quizId}/attempts";
    public static final String ATTEMPT_GET_BY_QUIZ         = "/api/quizzes/{quizId}/attempts";

    // ── Error / validation message constants ────────────────────────────────
    public static final String INVALID_UUID_FORMAT         = "Invalid UUID format: ";
    public static final String INVALID_UUID_STRING         = "Invalid UUID string";
    public static final String INVALID_UUID_GENERIC        = "Invalid UUID format";

    // ResourceNotFoundException entity names
    public static final String ENTITY_QUIZ                 = "Quiz";
    public static final String ENTITY_QUESTION             = "Question";
    public static final String ENTITY_MODULE               = "Module";

    // ResourceNotFoundException field names
    public static final String FIELD_ID                    = "id";
    public static final String FIELD_TITLE                 = "title";

    // DeletionNotAllowedException messages
    public static final String DELETE_QUIZ_HAS_QUESTIONS_PREFIX  = "Cannot delete quiz with ID ";
    public static final String DELETE_QUIZ_HAS_QUESTIONS_MID     = " because it contains ";
    public static final String DELETE_QUIZ_HAS_QUESTIONS_SUFFIX  = " question(s). Please remove all questions before deleting the quiz.";
    public static final String DELETE_QUIZ_HAS_ATTEMPTS_MID      = " because it has ";
    public static final String DELETE_QUIZ_HAS_ATTEMPTS_SUFFIX   = " attempt(s). Quizzes with student attempts cannot be deleted.";
}
