# Profile Service API Documentation

## Base URL
```
http://localhost:<port>
```

---

## User Controller Endpoints

Base Path: `/api/user`

---

### 1. Register User

**Endpoint:** `POST /api/user/register`

**Description:** Registers a new user in the system.

**Request Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "email": "string (required, valid email)",
  "password": "string (required)",
  "role": "string (required)"
}
```

**Example Request:**
```json
{
  "email": "john.doe@example.com",
  "password": "SecurePassword123!",
  "role": "STUDENT"
}
```

**Response:** `201 Created`

**Response Body:**
```json
{
  "id": "number",
  "email": "string",
  "role": "string",
  "createdAt": "datetime (ISO 8601 format)"
}
```

**Example Response:**
```json
{
  "id": 1,
  "email": "john.doe@example.com",
  "role": "STUDENT",
  "createdAt": "2026-02-09T10:30:00"
}
```

**Error Responses:**
- `400 Bad Request` - Validation error (invalid email, missing required fields)

---

### 2. Login User

**Endpoint:** `POST /api/user/login`

**Description:** Authenticates a user and returns a JWT token.

**Request Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "email": "string (required)",
  "password": "string (required)"
}
```

**Example Request:**
```json
{
  "email": "john.doe@example.com",
  "password": "SecurePassword123!"
}
```

**Response:** `200 OK`

**Response Body:**
```json
"JWT_TOKEN_STRING"
```

**Example Response:**
```json
"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
```

**Error Responses:**
- `401 Unauthorized` - Invalid credentials
```json
"Invalid Credentials"
```

---

### 3. Get User by Email

**Endpoint:** `GET /api/user/view/{email}`

**Description:** Retrieves user information by email address.

**Path Parameters:**
- `email` (string, required) - User's email address

**Request Headers:**
```
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>
```

**Response:** `200 OK`

**Response Body:**
```json
{
  "id": "number",
  "email": "string",
  "role": "string",
  "createdAt": "datetime (ISO 8601 format)"
}
```

**Example Response:**
```json
{
  "id": 1,
  "email": "john.doe@example.com",
  "role": "STUDENT",
  "createdAt": "2026-02-09T10:30:00"
}
```

**Error Responses:**
- `404 Not Found` - User not found
```json
{
  "error": "User is not found"
}
```

---

### 4. Get All Users

**Endpoint:** `GET /api/user/veiwAll`

**Description:** Retrieves a list of all users in the system.

**Request Headers:**
```
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>
```

**Response:** `200 OK`

**Response Body:**
```json
[
  {
    "id": "number",
    "email": "string",
    "role": "string",
    "createdAt": "datetime (ISO 8601 format)"
  }
]
```

**Example Response:**
```json
[
  {
    "id": 1,
    "email": "john.doe@example.com",
    "role": "STUDENT",
    "createdAt": "2026-02-09T10:30:00"
  },
  {
    "id": 2,
    "email": "jane.smith@example.com",
    "role": "TEACHER",
    "createdAt": "2026-02-09T11:45:00"
  }
]
```

---

### 5. Update User

**Endpoint:** `PUT /api/user/update/{id}`

**Description:** Updates user information (password and/or role).

**Path Parameters:**
- `id` (number, required) - User's ID

**Request Headers:**
```
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>
```

**Request Body:**
```json
{
  "password": "string (optional)",
  "role": "string (optional)"
}
```

**Example Request:**
```json
{
  "password": "NewSecurePassword456!",
  "role": "TEACHER"
}
```

**Response:** `200 OK`

**Response Body:**
```json
{
  "id": "number",
  "email": "string",
  "role": "string",
  "createdAt": "datetime (ISO 8601 format)"
}
```

**Example Response:**
```json
{
  "id": 1,
  "email": "john.doe@example.com",
  "role": "TEACHER",
  "createdAt": "2026-02-09T10:30:00"
}
```

**Error Responses:**
- `404 Not Found` - User not found
```json
{
  "error": "User not found"
}
```

---

### 6. Delete User

**Endpoint:** `DELETE /api/user/delete/{email}`

**Description:** Deletes a user from the system by email address.

**Path Parameters:**
- `email` (string, required) - User's email address

**Request Headers:**
```
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>
```

**Response:** `200 OK`

**Response Body:**
```json
{
  "id": "number",
  "email": "string",
  "role": "string",
  "createdAt": "datetime (ISO 8601 format)"
}
```

**Example Response:**
```json
{
  "id": 1,
  "email": "john.doe@example.com",
  "role": "STUDENT",
  "createdAt": "2026-02-09T10:30:00"
}
```

**Error Responses:**
- `404 Not Found` - User not found
```json
{
  "error": "User is not found"
}
```

---

## Profile Controller Endpoints

Base Path: `/api/profile`

---

### 1. Create Profile

**Endpoint:** `POST /api/profile/{id}`

**Description:** Creates a new profile for a user.

**Path Parameters:**
- `id` (number, required) - User's ID

**Request Headers:**
```
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>
```

**Request Body:**
```json
{
  "fullName": "string (optional)",
  "bio": "string (optional)",
  "imageUrl": "string (optional)",
  "socailMediaUrl": ["string array (optional)"]
}
```

**Example Request:**
```json
{
  "fullName": "John Doe",
  "bio": "Software Engineer with 5 years of experience",
  "imageUrl": "https://example.com/images/john-doe.jpg",
  "socailMediaUrl": [
    "https://linkedin.com/in/johndoe",
    "https://github.com/johndoe",
    "https://twitter.com/johndoe"
  ]
}
```

**Response:** `200 OK`

**Response Body:**
```json
{
  "fullName": "string",
  "bio": "string",
  "imageUrl": "string",
  "socailMediaUrl": ["string array"]
}
```

**Example Response:**
```json
{
  "fullName": "John Doe",
  "bio": "Software Engineer with 5 years of experience",
  "imageUrl": "https://example.com/images/john-doe.jpg",
  "socailMediaUrl": [
    "https://linkedin.com/in/johndoe",
    "https://github.com/johndoe",
    "https://twitter.com/johndoe"
  ]
}
```

**Error Responses:**
- `404 Not Found` - User not found
```json
{
  "error": "User not found"
}
```

---

### 2. Get Profile

**Endpoint:** `GET /api/profile/{id}`

**Description:** Retrieves a user's profile information by user ID.

**Path Parameters:**
- `id` (number, required) - User's ID

**Request Headers:**
```
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>
```

**Response:** `200 OK`

**Response Body:**
```json
{
  "fullName": "string",
  "bio": "string",
  "imageUrl": "string",
  "socailMediaUrl": ["string array"]
}
```

**Example Response:**
```json
{
  "fullName": "John Doe",
  "bio": "Software Engineer with 5 years of experience",
  "imageUrl": "https://example.com/images/john-doe.jpg",
  "socailMediaUrl": [
    "https://linkedin.com/in/johndoe",
    "https://github.com/johndoe",
    "https://twitter.com/johndoe"
  ]
}
```

**Error Responses:**
- `404 Not Found` - Profile not found
```json
{
  "error": "Profile Not Found"
}
```

---

### 3. Update Profile

**Endpoint:** `PUT /api/profile/{id}`

**Description:** Updates a user's profile information. All fields are optional.

**Path Parameters:**
- `id` (number, required) - User's ID

**Request Headers:**
```
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>
```

**Request Body:**
```json
{
  "fullName": "string (optional)",
  "bio": "string (optional)",
  "imageUrl": "string (optional)",
  "socailMediaUrl": ["string array (optional)"]
}
```

**Example Request:**
```json
{
  "bio": "Senior Software Engineer with 6 years of experience",
  "imageUrl": "https://example.com/images/john-doe-updated.jpg"
}
```

**Response:** `200 OK`

**Response Body:**
```json
{
  "fullName": "string",
  "bio": "string",
  "imageUrl": "string",
  "socailMediaUrl": ["string array"]
}
```

**Example Response:**
```json
{
  "fullName": "John Doe",
  "bio": "Senior Software Engineer with 6 years of experience",
  "imageUrl": "https://example.com/images/john-doe-updated.jpg",
  "socailMediaUrl": [
    "https://linkedin.com/in/johndoe",
    "https://github.com/johndoe",
    "https://twitter.com/johndoe"
  ]
}
```

**Notes:**
- If no request body is provided, the current profile data is returned without changes
- Only fields provided in the request body will be updated

**Error Responses:**
- `404 Not Found` - Profile not found
```json
{
  "error": "Profile Not Found"
}
```

---

### 4. Delete Profile

**Endpoint:** `DELETE /api/profile/{id}`

**Description:** Deletes a user's profile.

**Path Parameters:**
- `id` (number, required) - User's ID

**Request Headers:**
```
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>
```

**Response:** `200 OK`

**Response Body:**
```json
"Profile deleted successfully"
```

**Error Responses:**
- `404 Not Found` - Profile not found
```json
{
  "error": "Profile Not Found"
}
```

---

## Common Error Responses

### 400 Bad Request
```json
{
  "timestamp": "datetime",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation error message"
}
```

### 401 Unauthorized
```json
{
  "timestamp": "datetime",
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid or missing JWT token"
}
```

### 404 Not Found
```json
{
  "timestamp": "datetime",
  "status": 404,
  "error": "Not Found",
  "message": "Resource not found"
}
```

### 500 Internal Server Error
```json
{
  "timestamp": "datetime",
  "status": 500,
  "error": "Internal Server Error",
  "message": "An unexpected error occurred"
}
```

---

## Authentication

Most endpoints require JWT authentication. Include the JWT token in the Authorization header:

```
Authorization: Bearer <your_jwt_token>
```

To obtain a JWT token, use the `/api/user/login` endpoint with valid credentials.

---

## Data Models

### UserRegistrationDto
```json
{
  "email": "string (required, valid email format)",
  "password": "string (required)",
  "role": "string (required)"
}
```

### UserResponseDto
```json
{
  "id": "number",
  "email": "string",
  "role": "string",
  "createdAt": "datetime (ISO 8601 format)"
}
```

### UserUpdateDto
```json
{
  "password": "string (optional)",
  "role": "string (optional)"
}
```

### ProfileDto
```json
{
  "fullName": "string (optional)",
  "bio": "string (optional)",
  "imageUrl": "string (optional)",
  "socailMediaUrl": ["string array (optional)"]
}
```

---

## Notes

1. **Date Format:** All datetime fields follow ISO 8601 format (e.g., `2026-02-09T10:30:00`)
2. **Security:** Passwords are encrypted using BCrypt before storage
3. **JWT Token:** Tokens are generated using HS256 algorithm
4. **User Roles:** Common roles include `STUDENT`, `TEACHER`, `ADMIN`
5. **Profile Creation:** A profile can only be created after a user is registered
6. **Validation:** Email format and required fields are validated on the server side

---

## Example Usage Flow

1. **Register a new user:**
   ```
   POST /api/user/register
   ```

2. **Login to get JWT token:**
   ```
   POST /api/user/login
   ```

3. **Create a profile for the user:**
   ```
   POST /api/profile/{userId}
   ```

4. **Retrieve user profile:**
   ```
   GET /api/profile/{userId}
   ```

5. **Update profile information:**
   ```
   PUT /api/profile/{userId}
   ```

---

## Postman Collection

To import these endpoints into Postman, you can create a collection with the following structure:

- **Profile Service**
  - **User Endpoints**
    - Register User
    - Login User
    - Get User by Email
    - Get All Users
    - Update User
    - Delete User
  - **Profile Endpoints**
    - Create Profile
    - Get Profile
    - Update Profile
    - Delete Profile

---

## Contact & Support

For any issues or questions regarding the API, please contact the development team.

**Last Updated:** February 9, 2026
