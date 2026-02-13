# Classroom Service API Documentation

## Base Information
- **Service Name**: Classroom Service
- **Base URL**: `http://localhost:8092`
- **API Prefix**: `/api/classrooms`
- **Server Port**: 8092

## Overview
The Classroom Service provides RESTful APIs for managing classrooms in the OpenTutor platform. This service handles classroom creation, retrieval, updates, and deletion operations.

---

## Endpoints

### 1. Create Classroom

**Endpoint:** `POST /api/classrooms`

**Description:** Creates a new classroom with the provided details.

**Request Headers:**
- `Content-Type: application/json`

**Request Body:**
```json
{
  "teacherId": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Introduction to Java Programming",
  "subject": "Computer Science",
  "bannerImage": "https://example.com/images/java-banner.jpg",
  "inviteCode": "ABC123"
}
```

**Request Body Schema:**
| Field | Type | Required | Validation | Description |
|-------|------|----------|------------|-------------|
| teacherId | UUID | Yes | Not null | Unique identifier of the teacher |
| title | String | Yes | 3-100 characters | Title of the classroom |
| subject | String | Yes | 3-50 characters | Subject of the classroom |
| bannerImage | String | No | - | URL to the classroom banner image |
| inviteCode | String | No | 6-10 characters | Unique invite code for students to join |

**Success Response:**
- **Status Code:** `201 CREATED`
- **Response Body:**
```json
{
  "id": 1,
  "teacherId": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Introduction to Java Programming",
  "subject": "Computer Science",
  "bannerImage": "https://example.com/images/java-banner.jpg",
  "inviteCode": "ABC123",
  "isActive": true
}
```

**Error Responses:**
- **Status Code:** `400 BAD REQUEST` - Invalid request body or validation failure
```json
{
  "timestamp": "2026-02-09T10:15:30.00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed: Title is required"
}
```

---

### 2. Get All Classrooms

**Endpoint:** `GET /api/classrooms`

**Description:** Retrieves a list of all classrooms in the system.

**Request Headers:**
- None required

**Request Parameters:**
- None

**Success Response:**
- **Status Code:** `200 OK`
- **Response Body:**
```json
[
  {
    "id": 1,
    "teacherId": "550e8400-e29b-41d4-a716-446655440000",
    "title": "Introduction to Java Programming",
    "subject": "Computer Science",
    "bannerImage": "https://example.com/images/java-banner.jpg",
    "inviteCode": "ABC123",
    "isActive": true
  },
  {
    "id": 2,
    "teacherId": "660e8400-e29b-41d4-a716-446655440001",
    "title": "Advanced Python",
    "subject": "Computer Science",
    "bannerImage": "https://example.com/images/python-banner.jpg",
    "inviteCode": "XYZ789",
    "isActive": true
  }
]
```

**Error Responses:**
- **Status Code:** `500 INTERNAL SERVER ERROR` - Server error
```json
{
  "timestamp": "2026-02-09T10:15:30.00Z",
  "status": 500,
  "error": "Internal Server Error",
  "message": "An unexpected error occurred"
}
```

---

### 3. Get Classroom by ID

**Endpoint:** `GET /api/classrooms/{id}`

**Description:** Retrieves a specific classroom by its unique identifier.

**Request Headers:**
- None required

**Path Parameters:**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id | Long | Yes | Unique identifier of the classroom |

**Example Request:**
```
GET /api/classrooms/1
```

**Success Response:**
- **Status Code:** `200 OK`
- **Response Body:**
```json
{
  "id": 1,
  "teacherId": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Introduction to Java Programming",
  "subject": "Computer Science",
  "bannerImage": "https://example.com/images/java-banner.jpg",
  "inviteCode": "ABC123",
  "isActive": true
}
```

**Error Responses:**
- **Status Code:** `404 NOT FOUND` - Classroom not found
```json
{
  "timestamp": "2026-02-09T10:15:30.00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Classroom not found with id: 1"
}
```

---

### 4. Get Classrooms by Invite Code

**Endpoint:** `GET /api/classrooms/verify/{inviteCode}`

**Description:** Retrieves all classrooms matching the provided invite code. Used for classroom verification and joining.

**Request Headers:**
- None required

**Path Parameters:**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| inviteCode | String | Yes | Invite code of the classroom |

**Example Request:**
```
GET /api/classrooms/verify/ABC123
```

**Success Response:**
- **Status Code:** `200 OK`
- **Response Body:**
```json
[
  {
    "id": 1,
    "teacherId": "550e8400-e29b-41d4-a716-446655440000",
    "title": "Introduction to Java Programming",
    "subject": "Computer Science",
    "bannerImage": "https://example.com/images/java-banner.jpg",
    "inviteCode": "ABC123",
    "isActive": true
  }
]
```

**Error Responses:**
- **Status Code:** `200 OK` with empty array - No classrooms found with the invite code
```json
[]
```

---

### 5. Get Classrooms by Teacher ID

**Endpoint:** `GET /api/classrooms/teacher/{teacherId}`

**Description:** Retrieves all classrooms created by a specific teacher.

**Request Headers:**
- None required

**Path Parameters:**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| teacherId | UUID | Yes | Unique identifier of the teacher |

**Example Request:**
```
GET /api/classrooms/teacher/550e8400-e29b-41d4-a716-446655440000
```

**Success Response:**
- **Status Code:** `200 OK`
- **Response Body:**
```json
[
  {
    "id": 1,
    "teacherId": "550e8400-e29b-41d4-a716-446655440000",
    "title": "Introduction to Java Programming",
    "subject": "Computer Science",
    "bannerImage": "https://example.com/images/java-banner.jpg",
    "inviteCode": "ABC123",
    "isActive": true
  },
  {
    "id": 3,
    "teacherId": "550e8400-e29b-41d4-a716-446655440000",
    "title": "Advanced Data Structures",
    "subject": "Computer Science",
    "bannerImage": "https://example.com/images/ds-banner.jpg",
    "inviteCode": "DEF456",
    "isActive": true
  }
]
```

**Error Responses:**
- **Status Code:** `200 OK` with empty array - No classrooms found for the teacher
```json
[]
```

---

### 6. Update Classroom

**Endpoint:** `PUT /api/classrooms/{id}`

**Description:** Updates an existing classroom with the provided details.

**Request Headers:**
- `Content-Type: application/json`

**Path Parameters:**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id | Long | Yes | Unique identifier of the classroom to update |

**Request Body:**
```json
{
  "teacherId": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Introduction to Advanced Java Programming",
  "subject": "Computer Science",
  "bannerImage": "https://example.com/images/java-advanced-banner.jpg",
  "inviteCode": "ABC123"
}
```

**Request Body Schema:**
| Field | Type | Required | Validation | Description |
|-------|------|----------|------------|-------------|
| teacherId | UUID | Yes | Not null | Unique identifier of the teacher |
| title | String | Yes | 3-100 characters | Updated title of the classroom |
| subject | String | Yes | 3-50 characters | Updated subject of the classroom |
| bannerImage | String | No | - | Updated URL to the classroom banner image |
| inviteCode | String | No | 6-10 characters | Updated invite code |

**Example Request:**
```
PUT /api/classrooms/1
```

**Success Response:**
- **Status Code:** `200 OK`
- **Response Body:**
```json
{
  "id": 1,
  "teacherId": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Introduction to Advanced Java Programming",
  "subject": "Computer Science",
  "bannerImage": "https://example.com/images/java-advanced-banner.jpg",
  "inviteCode": "ABC123",
  "isActive": true
}
```

**Error Responses:**
- **Status Code:** `404 NOT FOUND` - Classroom not found
```json
{
  "timestamp": "2026-02-09T10:15:30.00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Classroom not found with id: 1"
}
```

- **Status Code:** `400 BAD REQUEST` - Invalid request body or validation failure
```json
{
  "timestamp": "2026-02-09T10:15:30.00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed: Title is required"
}
```

---

### 7. Delete Classroom

**Endpoint:** `DELETE /api/classrooms/{id}`

**Description:** Deletes a specific classroom by its unique identifier.

**Request Headers:**
- None required

**Path Parameters:**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id | Long | Yes | Unique identifier of the classroom to delete |

**Example Request:**
```
DELETE /api/classrooms/1
```

**Success Response:**
- **Status Code:** `204 NO CONTENT`
- **Response Body:** None

**Error Responses:**
- **Status Code:** `404 NOT FOUND` - Classroom not found
```json
{
  "timestamp": "2026-02-09T10:15:30.00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Classroom not found with id: 1"
}
```

---

## Data Models

### ClassroomRequestDTO

Request payload for creating or updating a classroom.

```json
{
  "teacherId": "UUID (required)",
  "title": "String (required, 3-100 chars)",
  "subject": "String (required, 3-50 chars)",
  "bannerImage": "String (optional)",
  "inviteCode": "String (optional, 6-10 chars)"
}
```

### ClassroomResponseDTO

Response payload containing classroom details.

```json
{
  "id": "Long",
  "teacherId": "UUID",
  "title": "String",
  "subject": "String",
  "bannerImage": "String",
  "inviteCode": "String",
  "isActive": "Boolean"
}
```

---

## Common Error Response Structure

All error responses follow this standard structure:

```json
{
  "timestamp": "ISO-8601 DateTime",
  "status": "HTTP Status Code",
  "error": "Error Type",
  "message": "Detailed error message"
}
```

---

## HTTP Status Codes Used

| Status Code | Description |
|-------------|-------------|
| 200 OK | Request succeeded |
| 201 CREATED | Resource created successfully |
| 204 NO CONTENT | Resource deleted successfully |
| 400 BAD REQUEST | Invalid request body or validation failure |
| 404 NOT FOUND | Resource not found |
| 500 INTERNAL SERVER ERROR | Server error |

---

## Usage Examples

### Using cURL

#### Create a classroom:
```bash
curl -X POST http://localhost:8092/api/classrooms \
  -H "Content-Type: application/json" \
  -d '{
    "teacherId": "550e8400-e29b-41d4-a716-446655440000",
    "title": "Introduction to Java Programming",
    "subject": "Computer Science",
    "bannerImage": "https://example.com/images/java-banner.jpg",
    "inviteCode": "ABC123"
  }'
```

#### Get all classrooms:
```bash
curl -X GET http://localhost:8092/api/classrooms
```

#### Get classroom by ID:
```bash
curl -X GET http://localhost:8092/api/classrooms/1
```

#### Get classrooms by invite code:
```bash
curl -X GET http://localhost:8092/api/classrooms/verify/ABC123
```

#### Get classrooms by teacher ID:
```bash
curl -X GET http://localhost:8092/api/classrooms/teacher/550e8400-e29b-41d4-a716-446655440000
```

#### Update a classroom:
```bash
curl -X PUT http://localhost:8092/api/classrooms/1 \
  -H "Content-Type: application/json" \
  -d '{
    "teacherId": "550e8400-e29b-41d4-a716-446655440000",
    "title": "Introduction to Advanced Java Programming",
    "subject": "Computer Science",
    "bannerImage": "https://example.com/images/java-advanced-banner.jpg",
    "inviteCode": "ABC123"
  }'
```

#### Delete a classroom:
```bash
curl -X DELETE http://localhost:8092/api/classrooms/1
```

---

## Notes

1. **UUID Format**: All UUID fields must be in the standard UUID format (e.g., `550e8400-e29b-41d4-a716-446655440000`)
2. **Validation**: All requests with a body are validated using Jakarta Bean Validation annotations
3. **Invite Codes**: Invite codes should be unique across classrooms for proper verification
4. **Banner Images**: Banner image URLs should be publicly accessible
5. **Active Status**: The `isActive` field in the response indicates whether the classroom is currently active
6. **Database**: The service uses PostgreSQL (via Supabase) for data persistence

---

## Version Information

- **API Version**: 1.0
- **Last Updated**: February 13, 2026
- **Service**: Classroom Service
- **Server Port**: 8092
- **Framework**: Spring Boot
- **Database**: PostgreSQL (Supabase)

---

## Support

For issues or questions regarding this API, please contact the development team or refer to the main project documentation.
