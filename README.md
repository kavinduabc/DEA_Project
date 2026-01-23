# OpenTutor: Open Learning Platform

## Project Overview
This repository hosts the source code for the Open Tutor Learning Platform. It is designed as a distributed system using a microservices architecture for the backend and a Next.js application for the frontend.

This project implements a complete microservices architecture with 8 distinct backend services and a Next.js frontend application to handle various aspects of the learning platform.

## Project Structure
- **backend/**: Contains the Spring Boot microservices.
    - **profile-service/**: Manages user identities and profiles.
    - **classroom-service/**: Handles classroom creation and management.
    - **resource-service/**: Manages learning resources and materials.
    - **enrollment-service/**: Handles student enrollments and course registrations.
    - **quiz-service/**: Manages quizzes and quiz submissions.
    - **assessment-service/**: Handles assessments and grading.
    - **qa-service/**: Manages questions and answers forum.
    - **announcement-service/**: Handles announcements and notifications.
- **frontend/**: Contains the Next.js web application.

## Prerequisites
Before running the applications, ensure you have the following installed on your environment:

- **Java Development Kit (JDK) 21**: Required for the backend services.
- **Node.js**: Required for the frontend application (LTS version recommended).
- **Maven**: (Optional) The project includes a Maven wrapper, so a global installation is not strictly necessary.

## Backend Setup (Spring Boot)

The backend services are located in the `backend` directory.

### Running the Profile Service

1. Navigate to the service directory:
   ```bash
   cd backend/{service-name}
   ```

2. Run the application using the Maven wrapper:
   - On Linux/macOS:
     ```bash
     mvn spring-boot:run
     ```
   - On Windows:
     ```cmd
     mvnw.cmd spring-boot:run
     ```

The service will start and initialize its configuration. Check the `application.yaml` file in `src/main/resources` for specific configuration details (e.g., server port, application name).

## Frontend Setup (Next.js)

The frontend application is located in the `frontend` directory.

### Installation

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install the dependencies:
   ```bash
   npm install
   # or
   yarn install
   # or
   pnpm install
   ```

### Running the Development Server

To start the frontend in development mode:

```bash
npm run dev
# or
yarn dev
# or
pnpm dev
```

Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

### Building for Production

To create an optimized production build:

```bash
npm run build
# or
yarn build
# or
pnpm build
```

To start the production server after building:

```bash
npm run start
# or
yarn start
# or
pnpm start
```

## Architecture Notes

This project follows a microservices architecture with the following services:

- **ProfileService**: Handles user data, authentication, and user profiles.
- **ClassroomService**: Manages classroom creation, updates, and classroom-related operations.
- **ResourceService**: Handles learning resources, materials, and content management.
- **EnrollmentService**: Manages student enrollments, course registrations, and enrollment status.
- **QuizService**: Handles quiz creation, quiz taking, and quiz submissions.
- **AssessmentService**: Manages assessments, grading, and evaluation of student work.
- **QAService**: Provides question and answer forum functionality for student interactions.
- **AnnouncementService**: Manages announcements, notifications, and communication within the platform.

Each service is independently deployable and communicates with other services through well-defined APIs.

## Contribution Guidelines

1. Clone the repository.
2. Create a feature branch for your changes.
3. Ensure all tests pass before committing.
4. Submit a pull request for review.
