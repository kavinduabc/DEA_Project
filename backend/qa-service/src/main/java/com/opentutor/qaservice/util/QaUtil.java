package com.opentutor.qaservice.util;

public class QaUtil {

    // Error message constants
    public static final String USER_NOT_FOUND = "User not found with ID: ";
    public static final String CLASSROOM_NOT_FOUND = "Classroom not found with ID: ";
    public static final String QUESTION_NOT_FOUND = "Question not found with ID: ";
    public static final String ANSWER_NOT_FOUND = "Answer not found with ID: ";
    public static final String QUESTION_HAS_ANSWERS_PREFIX = "Cannot delete question with ID ";
    public static final String QUESTION_HAS_ANSWERS_SUFFIX = " because it has ";
    public static final String QUESTION_HAS_ANSWERS_POSTFIX = " answer(s). Please remove all answers before deleting the question.";
    public static final String CANNOT_EDIT_ACCEPTED_ANSWER = "Cannot edit an accepted answer";
    public static final String CANNOT_DELETE_ACCEPTED_ANSWER = "Cannot delete an accepted answer. Please unmark it first.";

    // External service URL constants
    public static final String CLASSROOM_SERVICE_BASE_URL = "http://classroom-service";
    public static final String CLASSROOM_SERVICE_GET_BY_ID = "http://classroom-service/api/classrooms/{id}";

    public static final String PROFILE_SERVICE_BASE_URL = "http://profile-service";
    public static final String PROFILE_SERVICE_GET_USER_BY_ID = "http://profile-service/api/user/view/id/{id}";

    // QA Service endpoint path constants
    public static final String QA_BASE_PATH = "/api/qa";

    // Question sub-path constants (relative to QA_BASE_PATH, used in controller method mappings)
    public static final String QUESTIONS_PATH = "/questions";
    public static final String QUESTIONS_BY_ID_PATH = "/questions/{id}";
    public static final String QUESTIONS_BY_CLASSROOM_PATH = "/questions/classroom/{classroomId}";
    public static final String QUESTIONS_BY_USER_PATH = "/questions/user/{userId}";
    public static final String QUESTIONS_UNRESOLVED_PATH = "/questions/unresolved";
    public static final String QUESTIONS_UNRESOLVED_BY_CLASSROOM_PATH = "/questions/classroom/{classroomId}/unresolved";
    public static final String QUESTIONS_SEARCH_PATH = "/questions/search";
    public static final String QUESTIONS_RESOLVE_PATH = "/questions/{questionId}/resolve/{answerId}";
    public static final String QUESTIONS_UNRESOLVE_PATH = "/questions/{questionId}/unresolve";

    // Answer sub-path constants (relative to QA_BASE_PATH, used in controller method mappings)
    public static final String ANSWERS_PATH = "/answers";
    public static final String ANSWERS_BY_ID_PATH = "/answers/{id}";
    public static final String ANSWERS_BY_QUESTION_PATH = "/answers/question/{questionId}";
    public static final String ANSWERS_BY_USER_PATH = "/answers/user/{userId}";
    public static final String ANSWERS_ACCEPT_PATH = "/answers/{id}/accept";
    public static final String ANSWERS_UNACCEPT_PATH = "/answers/{id}/unaccept";
    public static final String ANSWERS_UPVOTE_PATH = "/answers/{id}/upvote";
    public static final String ANSWERS_REMOVE_UPVOTE_PATH = "/answers/{id}/remove-upvote";

}
