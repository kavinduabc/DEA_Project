# Enrollment Service API Documentation

## Base Information
- **Service Name**: Enrollment Service
- **Base URL**: `http://localhost:8094`
- **API Prefix**: `/api/enrollments`

## Overview
The Enrollment Service manages student enrollments in classrooms within the OpenTutor platform. It provides endpoints for creating, retrieving, and deleting enrollment records.

---

## Endpoints

### 1. Create Enrollment
Enroll a student in a classroom.

- **Endpoint**: `POST /api/enrollments`
- **Description**: Creates a new enrollment record for a student in a specified classroom
- **Authentication**: Required (assumed based on typical microservice patterns)

#### Request Body
```json
{
  "studentId": "UUID",
  "classroomId": "UUID"
}
```

**Field Descriptions:**
| Field | Type | Required | Description |
|-------|------|----------|-------------|
| `studentId` | UUID | Yes | The unique identifier of the student |
| `classroomId` | UUID | Yes | The unique identifier of the classroom |

#### Example Request
```bash
curl -X POST http://localhost:8094/api/enrollments \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": "550e8400-e29b-41d4-a716-446655440000",
    "classroomId": "650e8400-e29b-41d4-a716-446655440001"
  }'
```

#### Success Response
- **Status Code**: `201 CREATED`
- **Response Body**:
```json
{
  "id": "750e8400-e29b-41d4-a716-446655440002",
  "studentId": "550e8400-e29b-41d4-a716-446655440000",
  "classroomId": "650e8400-e29b-41d4-a716-446655440001"
}
```

**Response Field Descriptions:**
| Field | Type | Description |
|-------|------|-------------|
| `id` | UUID | The unique identifier of the enrollment record |
| `studentId` | UUID | The unique identifier of the student |
| `classroomId` | UUID | The unique identifier of the classroom |

#### Error Responses
- **Status Code**: `400 BAD REQUEST`
  - When required fields are missing or validation fails
  ```json
  {
    "timestamp": "2026-02-13T10:30:00",
    "message": "Validation failed",
    "details": "studentId is required"
  }
  ```

---

### 2. Get All Enrollments
Retrieve all enrollment records in the system.

- **Endpoint**: `GET /api/enrollments`
- **Description**: Returns a list of all enrollment records
- **Authentication**: Required

#### Example Request
```bash
curl -X GET http://localhost:8094/api/enrollments \
  -H "Content-Type: application/json"
```

#### Success Response
- **Status Code**: `200 OK`
- **Response Body**:
```json
[
  {
    "id": "750e8400-e29b-41d4-a716-446655440002",
    "studentId": "550e8400-e29b-41d4-a716-446655440000",
    "classroomId": "650e8400-e29b-41d4-a716-446655440001"
  },
  {
    "id": "850e8400-e29b-41d4-a716-446655440003",
    "studentId": "950e8400-e29b-41d4-a716-446655440004",
    "classroomId": "650e8400-e29b-41d4-a716-446655440001"
  }
]
```

---

### 3. Get Enrollment by ID
Retrieve a specific enrollment record by its ID.

- **Endpoint**: `GET /api/enrollments/{id}`
- **Description**: Returns details of a single enrollment record
- **Authentication**: Required

#### Path Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| `id` | UUID | Yes | The unique identifier of the enrollment |

#### Example Request
```bash
curl -X GET http://localhost:8094/api/enrollments/750e8400-e29b-41d4-a716-446655440002 \
  -H "Content-Type: application/json"
```

#### Success Response
- **Status Code**: `200 OK`
- **Response Body**:
```json
{
  "id": "750e8400-e29b-41d4-a716-446655440002",
  "studentId": "550e8400-e29b-41d4-a716-446655440000",
  "classroomId": "650e8400-e29b-41d4-a716-446655440001"
}
```

#### Error Responses
- **Status Code**: `404 NOT FOUND`
  - When enrollment with the specified ID does not exist
  - Response body is empty

---

### 4. Get Enrollments by Student ID
Retrieve all enrollments for a specific student.

- **Endpoint**: `GET /api/enrollments/student/{studentId}`
- **Description**: Returns all classroom enrollments for a given student
- **Authentication**: Required

#### Path Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| `studentId` | UUID | Yes | The unique identifier of the student |

#### Example Request
```bash
curl -X GET http://localhost:8094/api/enrollments/student/550e8400-e29b-41d4-a716-446655440000 \
  -H "Content-Type: application/json"
```

#### Success Response
- **Status Code**: `200 OK`
- **Response Body**:
```json
[
  {
    "id": "750e8400-e29b-41d4-a716-446655440002",
    "studentId": "550e8400-e29b-41d4-a716-446655440000",
    "classroomId": "650e8400-e29b-41d4-a716-446655440001"
  },
  {
    "id": "a50e8400-e29b-41d4-a716-446655440005",
    "studentId": "550e8400-e29b-41d4-a716-446655440000",
    "classroomId": "b50e8400-e29b-41d4-a716-446655440006"
  }
]
```

**Note**: Returns an empty array `[]` if the student has no enrollments.

---

### 5. Get Enrollments by Classroom ID
Retrieve all enrollments for a specific classroom.

- **Endpoint**: `GET /api/enrollments/classroom/{classroomId}`
- **Description**: Returns all student enrollments for a given classroom
- **Authentication**: Required

#### Path Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| `classroomId` | UUID | Yes | The unique identifier of the classroom |

#### Example Request
```bash
curl -X GET http://localhost:8094/api/enrollments/classroom/650e8400-e29b-41d4-a716-446655440001 \
  -H "Content-Type: application/json"
```

#### Success Response
- **Status Code**: `200 OK`
- **Response Body**:
```json
[
  {
    "id": "750e8400-e29b-41d4-a716-446655440002",
    "studentId": "550e8400-e29b-41d4-a716-446655440000",
    "classroomId": "650e8400-e29b-41d4-a716-446655440001"
  },
  {
    "id": "850e8400-e29b-41d4-a716-446655440003",
    "studentId": "950e8400-e29b-41d4-a716-446655440004",
    "classroomId": "650e8400-e29b-41d4-a716-446655440001"
  }
]
```

**Note**: Returns an empty array `[]` if the classroom has no enrollments.

---

### 6. Delete Enrollment
Remove an enrollment record from the system.

- **Endpoint**: `DELETE /api/enrollments/{id}`
- **Description**: Deletes an enrollment record by its ID
- **Authentication**: Required

#### Path Parameters
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| `id` | UUID | Yes | The unique identifier of the enrollment to delete |

#### Example Request
```bash
curl -X DELETE http://localhost:8094/api/enrollments/750e8400-e29b-41d4-a716-446655440002 \
  -H "Content-Type: application/json"
```

#### Success Response
- **Status Code**: `204 NO CONTENT`
- **Response Body**: Empty

#### Error Responses
- **Status Code**: `404 NOT FOUND`
  - When enrollment with the specified ID does not exist
  - May include error details depending on exception handling

---

## Data Models

### EnrollmentRequestDTO
Used when creating a new enrollment.

```json
{
  "studentId": "UUID (required)",
  "classroomId": "UUID (required)"
}
```

### EnrollmentResponseDTO
Returned when querying enrollment data.

```json
{
  "id": "UUID",
  "studentId": "UUID",
  "classroomId": "UUID"
}
```

### ErrorResponseDTO
Returned when an error occurs.

```json
{
  "timestamp": "ISO 8601 DateTime",
  "message": "string",
  "details": "string"
}
```

---

## Common HTTP Status Codes

| Status Code | Description |
|-------------|-------------|
| `200 OK` | Request succeeded |
| `201 CREATED` | Resource successfully created |
| `204 NO CONTENT` | Request succeeded with no response body |
| `400 BAD REQUEST` | Invalid request data or validation failure |
| `404 NOT FOUND` | Requested resource does not exist |
| `500 INTERNAL SERVER ERROR` | Server-side error occurred |

---

## Notes

1. **UUID Format**: All IDs use the UUID format (e.g., `550e8400-e29b-41d4-a716-446655440000`)

2. **Validation**: The service validates that both `studentId` and `classroomId` are provided in the request

3. **Idempotency**: The DELETE operation is idempotent - deleting the same enrollment multiple times will return success after the first deletion

4. **Database**: The service uses PostgreSQL (via Supabase) for data persistence

5. **Service Dependencies**: This service likely depends on:
   - Profile Service (for student validation)
   - Classroom Service (for classroom validation)

---

## Testing Examples

### Creating an Enrollment
```bash
# Create a new enrollment
curl -X POST http://localhost:8094/api/enrollments \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": "550e8400-e29b-41d4-a716-446655440000",
    "classroomId": "650e8400-e29b-41d4-a716-446655440001"
  }'
```

### Querying Enrollments
```bash
# Get all enrollments
curl -X GET http://localhost:8094/api/enrollments

# Get specific enrollment
curl -X GET http://localhost:8094/api/enrollments/750e8400-e29b-41d4-a716-446655440002

# Get enrollments by student
curl -X GET http://localhost:8094/api/enrollments/student/550e8400-e29b-41d4-a716-446655440000

# Get enrollments by classroom
curl -X GET http://localhost:8094/api/enrollments/classroom/650e8400-e29b-41d4-a716-446655440001
```

### Deleting an Enrollment
```bash
# Delete an enrollment
curl -X DELETE http://localhost:8094/api/enrollments/750e8400-e29b-41d4-a716-446655440002
```

---

## Version Information
- **API Version**: 1.0
- **Last Updated**: February 13, 2026
- **Service Port**: 8094
