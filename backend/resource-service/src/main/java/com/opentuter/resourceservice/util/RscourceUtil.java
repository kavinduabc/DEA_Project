package com.opentuter.resourceservice.util;

public class RscourceUtil {

    // External service URLs
    public static final String CLASSROOM_SERVICE_BASE_URL = "http://classroom-service";
    public static final String CLASSROOM_SERVICE_GET_BY_ID = "/api/classrooms/{id}";

    // Resource-service base path
    public static final String RESOURCE_BASE_PATH = "/api/resources";

    // Module sub-path constants
    public static final String MODULES_PATH = "/modules";
    public static final String MODULES_BY_ID_PATH = "/modules/{id}";

    // Resource sub-path constants
    public static final String RESOURCE_CREATE_PATH = "/";
    public static final String RESOURCE_BY_ID_PATH = "/{id}";
    public static final String RESOURCES_BY_MODULE_PATH = "/module/{moduleId}";

    // Entity name constants
    public static final String ENTITY_MODULE = "Module";
    public static final String ENTITY_RESOURCE = "Resource";

    // Field name constants
    public static final String FIELD_ID = "id";

    // Error message constants
    public static final String CLASSROOM_NOT_FOUND_PREFIX = "Classroom not found with id: ";
    public static final String CLASSROOM_VALIDATION_ERROR_PREFIX = "Error while validating classroom with id: ";
}
