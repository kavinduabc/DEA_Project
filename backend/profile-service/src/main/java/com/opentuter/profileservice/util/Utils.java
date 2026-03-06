package com.opentuter.profileservice.util;

public class Utils {
    public static final String EMAIL_ALREADY_USE = "Email already exists";
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_DELETED = "User deleted successfully";
    public static final String BASE_URL = "/api/user";
    public static final String REGISTRATION_URL = "/reg";
    public static final String VERIFY_URL = "/login";
    public static final String VIEW_USER_BY_ID_URL = "/view/id/{id}";
    public static final String VIEW_USER_BY_EMAIL_URL = "/view/{email}";
    public static final String VIEW_USER_URL = "/views";
    public static final String UPDATE_USER_BY_ID_URL = "/update/{id}";
    public static final String DELETE_USER_BY_ID_URL = "/delete/{id}";
    public static final int tokenExpirationTime = 604800;
    public static final int BEGININDEX = 7;
    public static final String USER_TABLE = "users";
    public static final String PROFILE_TABLE = "profiles";
    public static final String ID = "id";
    public static final String ACCESS_URL_REGISTER = "/api/user/reg";
    public static final String ACCESS_URL_LOGIN = "/api/user/login";
    public static final String UNAOTHORIZED = "Unauthorized";
    public static final String FORBIDDEN = "Forbidden";
    public static final String UHF = "UTF-8";
    public static final String JSON_FORMAT = "{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\"}";
    public static final String ACCEPT_IMG_FORMAT = "/images/**";
    public static final String UPLOAD_DIR = "file:uploads/";
    public static final String EMAIL_VALIDE = "Email should be valid";
    public static final String EMAIL_NOT_BLANK = "Email cannot be blank";
    public static final String PASSWORD_NOT_BLANK = "Password cannot be blank";
    public static final String LOGIN_TO_STRING_LOGREQ= "LoginRequest{";
    public static final String LOGIN_TO_STRING_EMAIL = "email='";
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String ROLE_REQUIRED = "Role is required";
    public static final String NAME_REQUIRED = "Name is required";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String ROLE = "ROLE_";
    public static final String USER_CHECK_EMAIL = "User not found with email: ";
    public static final String SECRET_KEY = "77a955704741b6d5c2bcf34a89b95ba0d1ccbb22dbba9eeeceec78adc5fcc7b2";
    public static final String ROLE_S = "role";
    public static final String SWAGGER_TITLE = "Profile Service APIs";
    public static final String SWAGGER_DESCRIPTION = "Profile Service API documentation of OpenTuror Learing Platform";
    public static final String SWAGGER_VERSION = "1.0";




}