# Profile Service API Documentation

## Overview
The Profile Service is a microservice that manages user profiles, authentication, and user account operations. This service provides RESTful endpoints for user registration, login, profile management, and user CRUD operations.

**Base URL:** `/api/user`

**Version:** 1.0.0

---

## Table of Contents
- [Authentication](#authentication)
- [Endpoints](#endpoints)
  - [Register User](#1-register-user)
  - [Login User](#2-login-user)
  - [Get User by Email](#3-get-user-by-email)
  - [Get All Users](#4-get-all-users)
  - [Update User](#5-update-user)
  - [Delete User](#6-delete-user)
- [Data Models](#data-models)
- [Error Responses](#error-responses)
- [Example Usage](#example-usage)

---

## Authentication

Most endpoints require authentication via JWT (JSON Web Token). After successful login, include the JWT token in the Authorization header:

```
Authorization: Bearer <your_jwt_token>
```

---

## Endpoints

### 1. Register User

Register a new user in the system.

**Endpoint:** `POST /api/user/reg`

**Authentication:** Not required

**Request Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "email": "string (required, valid email)",
  "password": "string (required)",
  "role": "string (required)",
  "fullName": "string (required)",
  "bio": "string (optional)",
  "imageUrl": "string (optional)",
  "socialMediaUrls": ["string"] (optional)
}
```

**Request Body Example:**
```json
{
  "email": "john.doe@example.com",
  "password": "SecurePassword123!",
  "role": "STUDENT",
  "fullName": "John Doe",
  "bio": "Computer Science student interested in AI and Machine Learning",
  "imageUrl": "https://example.com/images/johndoe.jpg",
  "socialMediaUrls": [
    "https://linkedin.com/in/johndoe",
    "https://github.com/johndoe"
  ]
}
```

**Success Response:**
- **Status Code:** `201 CREATED`
- **Response Body:**
```json
{
  "id": "uuid",
  "email": "string",
  "role": "string",
  "fullName": "string",
  "bio": "string",
  "imageUrl": "string",
  "socialMediaUrls": ["string"]
}
```

**Success Response Example:**
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "email": "john.doe@example.com",
  "role": "STUDENT",
  "fullName": "John Doe",
  "bio": "Computer Science student interested in AI and Machine Learning",
  "imageUrl": "https://example.com/images/johndoe.jpg",
  "socialMediaUrls": [
    "https://linkedin.com/in/johndoe",
    "https://github.com/johndoe"
  ]
}
```

**Error Responses:**
- `400 Bad Request` - Invalid input data or validation errors
- `409 Conflict` - Email already exists

---

### 2. Login User

Authenticate a user and receive a JWT token.

**Endpoint:** `POST /api/user/login`

**Authentication:** Not required

**Request Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "email": "string (required, valid email)",
  "password": "string (required)"
}
```

**Request Body Example:**
```json
{
  "email": "john.doe@example.com",
  "password": "SecurePassword123!"
}
```

**Success Response:**
- **Status Code:** `200 OK`
- **Response Body:**
```json
{
  "token": "string",
  "email": "string",
  "role": "string",
  "expiresAt": "ISO 8601 timestamp"
}
```

**Success Response Example:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsInJvbGUiOiJTVFVERU5UIiwiaWF0IjoxNjQwOTk1MjAwLCJleHAiOjE2NDA5OTg4MDB9.abc123def456",
  "email": "john.doe@example.com",
  "role": "STUDENT",
  "expiresAt": "2026-02-13T10:00:00Z"
}
```

**Error Responses:**
- `400 Bad Request` - Invalid input data
- `401 Unauthorized` - Invalid credentials

---

### 3. Get User by Email

Retrieve user profile information by email address.

**Endpoint:** `GET /api/user/view/{email}`

**Authentication:** Required

**Path Parameters:**
- `email` (string, required) - The email address of the user

**Request Headers:**
```
Authorization: Bearer <jwt_token>
```

**Request Example:**
```
GET /api/user/view/john.doe@example.com
```

**Success Response:**
- **Status Code:** `200 OK`
- **Response Body:**
```json
{
  "id": "uuid",
  "email": "string",
  "role": "string",
  "fullName": "string",
  "bio": "string",
  "imageUrl": "string",
  "socialMediaUrls": ["string"]
}
```

**Success Response Example:**
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "email": "john.doe@example.com",
  "role": "STUDENT",
  "fullName": "John Doe",
  "bio": "Computer Science student interested in AI and Machine Learning",
  "imageUrl": "https://example.com/images/johndoe.jpg",
  "socialMediaUrls": [
    "https://linkedin.com/in/johndoe",
    "https://github.com/johndoe"
  ]
}
```

**Error Responses:**
- `401 Unauthorized` - Missing or invalid JWT token
- `404 Not Found` - User not found

---

### 4. Get All Users

Retrieve a list of all users in the system.

**Endpoint:** `GET /api/user/views`

**Authentication:** Required (typically admin role)

**Request Headers:**
```
Authorization: Bearer <jwt_token>
```

**Success Response:**
- **Status Code:** `200 OK`
- **Response Body:**
```json
[
  {
    "id": "uuid",
    "email": "string",
    "role": "string",
    "fullName": "string",
    "bio": "string",
    "imageUrl": "string",
    "socialMediaUrls": ["string"]
  }
]
```

**Success Response Example:**
```json
[
  {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "email": "john.doe@example.com",
    "role": "STUDENT",
    "fullName": "John Doe",
    "bio": "Computer Science student interested in AI and Machine Learning",
    "imageUrl": "https://example.com/images/johndoe.jpg",
    "socialMediaUrls": [
      "https://linkedin.com/in/johndoe",
      "https://github.com/johndoe"
    ]
  },
  {
    "id": "234e5678-e89b-12d3-a456-426614174001",
    "email": "jane.smith@example.com",
    "role": "TEACHER",
    "fullName": "Jane Smith",
    "bio": "Mathematics teacher with 10 years of experience",
    "imageUrl": "https://example.com/images/janesmith.jpg",
    "socialMediaUrls": [
      "https://linkedin.com/in/janesmith"
    ]
  }
]
```

**Error Responses:**
- `401 Unauthorized` - Missing or invalid JWT token
- `403 Forbidden` - Insufficient permissions

---

### 5. Update User

Update user profile information.

**Endpoint:** `PUT /api/user/update/{id}`

**Authentication:** Required

**Path Parameters:**
- `id` (uuid, required) - The unique identifier of the user

**Request Headers:**
```
Authorization: Bearer <jwt_token>
Content-Type: application/json
```

**Request Body:**
All fields are optional. Only include fields that need to be updated.
```json
{
  "email": "string (optional)",
  "role": "string (optional)",
  "fullName": "string (optional)",
  "bio": "string (optional)",
  "imageUrl": "string (optional)",
  "socialMediaUrls": ["string"] (optional)
}
```

**Request Example:**
```
PUT /api/user/update/123e4567-e89b-12d3-a456-426614174000
```

**Request Body Example:**
```json
{
  "bio": "Updated bio: Senior Computer Science student specializing in AI",
  "imageUrl": "https://example.com/images/johndoe-new.jpg",
  "socialMediaUrls": [
    "https://linkedin.com/in/johndoe",
    "https://github.com/johndoe",
    "https://twitter.com/johndoe"
  ]
}
```

**Success Response:**
- **Status Code:** `200 OK`
- **Response Body:**
```json
{
  "id": "uuid",
  "email": "string",
  "role": "string",
  "fullName": "string",
  "bio": "string",
  "imageUrl": "string",
  "socialMediaUrls": ["string"]
}
```

**Success Response Example:**
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "email": "john.doe@example.com",
  "role": "STUDENT",
  "fullName": "John Doe",
  "bio": "Updated bio: Senior Computer Science student specializing in AI",
  "imageUrl": "https://example.com/images/johndoe-new.jpg",
  "socialMediaUrls": [
    "https://linkedin.com/in/johndoe",
    "https://github.com/johndoe",
    "https://twitter.com/johndoe"
  ]
}
```

**Error Responses:**
- `400 Bad Request` - Invalid input data
- `401 Unauthorized` - Missing or invalid JWT token
- `404 Not Found` - User not found

---

### 6. Delete User

Delete a user account from the system.

**Endpoint:** `DELETE /api/user/delete/{email}`

**Authentication:** Required (typically admin role)

**Path Parameters:**
- `email` (string, required) - The email address of the user to delete

**Request Headers:**
```
Authorization: Bearer <jwt_token>
```

**Request Example:**
```
DELETE /api/user/delete/john.doe@example.com
```

**Success Response:**
- **Status Code:** `200 OK`
- **Response Body:** Returns the deleted user information
```json
{
  "id": "uuid",
  "email": "string",
  "role": "string",
  "fullName": "string",
  "bio": "string",
  "imageUrl": "string",
  "socialMediaUrls": ["string"]
}
```

**Success Response Example:**
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "email": "john.doe@example.com",
  "role": "STUDENT",
  "fullName": "John Doe",
  "bio": "Computer Science student interested in AI and Machine Learning",
  "imageUrl": "https://example.com/images/johndoe.jpg",
  "socialMediaUrls": [
    "https://linkedin.com/in/johndoe",
    "https://github.com/johndoe"
  ]
}
```

**Error Responses:**
- `401 Unauthorized` - Missing or invalid JWT token
- `403 Forbidden` - Insufficient permissions
- `404 Not Found` - User not found

---

## Data Models

### ProfileRequestDto

Used for user registration.

| Field | Type | Required | Validation | Description |
|-------|------|----------|------------|-------------|
| email | string | Yes | Valid email format | User's email address |
| password | string | Yes | Not blank | User's password |
| role | string | Yes | Not blank | User's role (e.g., STUDENT, TEACHER, ADMIN) |
| fullName | string | Yes | Not blank | User's full name |
| bio | string | No | - | User's biography or description |
| imageUrl | string | No | - | URL to user's profile image |
| socialMediaUrls | array[string] | No | - | List of social media profile URLs |

### ProfileUpdateDto

Used for updating user profile.

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| email | string | No | Updated email address |
| role | string | No | Updated user role |
| fullName | string | No | Updated full name |
| bio | string | No | Updated biography |
| imageUrl | string | No | Updated profile image URL |
| socialMediaUrls | array[string] | No | Updated social media URLs |

### ProfileResponseDto

Returned for all successful user operations.

| Field | Type | Description |
|-------|------|-------------|
| id | uuid | Unique user identifier |
| email | string | User's email address |
| role | string | User's role |
| fullName | string | User's full name |
| bio | string | User's biography |
| imageUrl | string | User's profile image URL |
| socialMediaUrls | array[string] | User's social media URLs |

### LoginRequestDto

Used for user authentication.

| Field | Type | Required | Validation | Description |
|-------|------|----------|------------|-------------|
| email | string | Yes | Valid email format | User's email address |
| password | string | Yes | Not blank | User's password |

### LoginResponseDto

Returned after successful login.

| Field | Type | Description |
|-------|------|-------------|
| token | string | JWT authentication token |
| email | string | User's email address |
| role | string | User's role |
| expiresAt | timestamp | Token expiration timestamp (ISO 8601 format) |

---

## Error Responses

All error responses follow a consistent format:

```json
{
  "timestamp": "ISO 8601 timestamp",
  "status": "HTTP status code",
  "error": "Error type",
  "message": "Detailed error message",
  "path": "Request path"
}
```

### Common Error Codes

| Status Code | Description |
|-------------|-------------|
| 400 | Bad Request - Invalid input or validation error |
| 401 | Unauthorized - Missing or invalid authentication |
| 403 | Forbidden - Insufficient permissions |
| 404 | Not Found - Resource not found |
| 409 | Conflict - Resource already exists |
| 500 | Internal Server Error - Server error |

**Example Error Response:**
```json
{
  "timestamp": "2026-02-13T10:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "User is not found",
  "path": "/api/user/view/nonexistent@example.com"
}
```

---

## Example Usage

### Complete User Registration Flow

#### Step 1: Register a New User
```bash
curl -X POST http://localhost:8080/api/user/reg \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "SecurePassword123!",
    "role": "STUDENT",
    "fullName": "John Doe",
    "bio": "Computer Science student",
    "imageUrl": "https://example.com/images/johndoe.jpg",
    "socialMediaUrls": ["https://linkedin.com/in/johndoe"]
  }'
```

#### Step 2: Login to Get JWT Token
```bash
curl -X POST http://localhost:8080/api/user/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "SecurePassword123!"
  }'
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "john.doe@example.com",
  "role": "STUDENT",
  "expiresAt": "2026-02-13T10:00:00Z"
}
```

#### Step 3: Get User Profile
```bash
curl -X GET http://localhost:8080/api/user/view/john.doe@example.com \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

#### Step 4: Update User Profile
```bash
curl -X PUT http://localhost:8080/api/user/update/123e4567-e89b-12d3-a456-426614174000 \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." \
  -H "Content-Type: application/json" \
  -d '{
    "bio": "Senior Computer Science student specializing in AI"
  }'
```

#### Step 5: Get All Users (Admin)
```bash
curl -X GET http://localhost:8080/api/user/views \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

#### Step 6: Delete User (Admin)
```bash
curl -X DELETE http://localhost:8080/api/user/delete/john.doe@example.com \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

---

## User Roles

The system supports the following user roles:

| Role | Description | Permissions |
|------|-------------|-------------|
| STUDENT | Regular student user | View and update own profile |
| TEACHER | Instructor user | View and update own profile, manage classes |
| ADMIN | Administrator user | Full access to all endpoints including user management |

---

## Security Considerations

1. **Password Storage:** Passwords are hashed using secure algorithms before storage
2. **JWT Tokens:** Tokens expire after a configurable time period
3. **CORS:** Cross-Origin Resource Sharing is enabled with appropriate restrictions
4. **Input Validation:** All inputs are validated on the server side
5. **Email Uniqueness:** Each email address can only be registered once

---

## Additional Notes

- **Base URL Configuration:** The actual base URL will depend on your deployment environment (e.g., `http://localhost:8080` for local development)
- **Date Format:** All timestamps follow ISO 8601 format (e.g., `2026-02-13T10:00:00Z`)
- **UUID Format:** All IDs are UUID v4 format
- **Content Type:** All requests and responses use `application/json`

---

## Postman Collection

To test these endpoints in Postman:

1. Create a new collection named "Profile Service"
2. Add the following environment variables:
   - `base_url`: Your service base URL (e.g., `http://localhost:8080`)
   - `jwt_token`: Set automatically after login
3. Import the endpoints as documented above
4. Use the login endpoint to obtain a JWT token
5. Set the token in the environment variable for authenticated requests

---

## Support

For issues, questions, or contributions, please contact the development team or refer to the project repository.

**Last Updated:** February 13, 2026
