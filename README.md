# resQ

resQ is a stray animal and bird rescue, response, and emergency management platform designed to help users report rescue cases, coordinate emergency responses, and manage animal and bird rescue operations.

The project consists of an Android mobile application and a Spring Boot backend REST API.

---

## Repository Structure

```
resQ/
├── resq-backend-main/       # Spring Boot REST API (Java)
└── ResqApp-main/            # Android Mobile App (Kotlin)
```

---

## Tech Stack

### Backend — resq-backend-main

- Java
- Spring Boot
- Maven
- REST API

### Mobile App — ResqApp-main

- Kotlin
- Android
- Gradle
- Android Studio

---

## Getting Started

### Prerequisites

Before running the project, make sure you have:

- Java JDK installed
- Android Studio installed
- Android SDK configured
- Git installed
- A compatible Android device or emulator

### Backend Setup

Navigate to the backend project:

```bash
cd resq-backend-main/resq-backend-main
```

Run the Spring Boot application using the Maven wrapper:

**Linux / macOS**
```bash
./mvnw spring-boot:run
```

**Windows**
```cmd
mvnw.cmd spring-boot:run
```

Once started, the backend REST API will be available on the configured Spring Boot port.

### Android App Setup

1. Open Android Studio.
2. Select `ResqApp-main/ResqApp-main` as the project directory.
3. Allow Android Studio to sync the Gradle project.
4. Configure an Android emulator or connect a physical Android device.
5. Build and run the application via **Run > Run 'app'**.

Make sure the Android application is configured to communicate with the running backend API.

---

## Project Architecture

```
+---------------------+
|   resQ Android      |
|   Mobile App        |
|   (Kotlin)          |
+----------+----------+
           |
           | REST API
           v
+---------------------+
|   resQ Backend      |
|   Spring Boot API   |
|   (Java)            |
+----------+----------+
           |
           v
+---------------------+
|   Database          |
+---------------------+
```

---

## Purpose

resQ aims to provide a centralized platform for managing stray animal and bird rescue emergencies.

Potential use cases include:

- Reporting injured or stranded animals
- Requesting emergency rescue assistance
- Reporting injured birds
- Managing rescue responses
- Tracking rescue locations
- Connecting citizens and rescue teams
- Managing rescue cases and their status
- Supporting emergency response coordination

---

## Main Components

### Mobile Application

The Android application provides the user-facing interface for interacting with the resQ platform.

It can be used to:

- Submit rescue requests
- Provide information about an animal or bird
- Share the rescue location
- Track submitted cases
- Receive updates from the backend

### Backend API

The Spring Boot backend provides the server-side functionality for the application.

Responsibilities include:

- Processing API requests
- Managing rescue cases
- Handling application data
- Providing REST endpoints
- Connecting the mobile application with backend services

---

## Project Directories

```
resQ/
|
+-- resq-backend-main/
|   +-- resq-backend-main/
|       +-- src/
|       +-- pom.xml
|       +-- mvnw
|
+-- ResqApp-main/
    +-- ResqApp-main/
        +-- app/
        +-- build.gradle
        +-- settings.gradle
```

The exact directory structure may vary depending on the current project implementation.

---

## Development

### Backend

Make backend changes inside:

```
resq-backend-main/resq-backend-main/
```

After making changes, restart the Spring Boot application:

```bash
./mvnw spring-boot:run
```

### Android

Make mobile application changes inside:

```
ResqApp-main/ResqApp-main/
```

Then sync Gradle and run the application through Android Studio.

---

## Testing

### Backend

Run the backend tests with:

```bash
./mvnw test
```

### Android

Android tests can be executed through Android Studio or Gradle:

```bash
./gradlew test
```

---

## Contributing

1. Clone the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature/your-feature
   ```
3. Make your changes.
4. Test the backend and Android application.
5. Commit your changes:
   ```bash
   git add .
   git commit -m "Add your feature"
   ```
6. Push the branch:
   ```bash
   git push origin feature/your-feature
   ```
7. Open a Pull Request.

---

## License

Add the project's license information here.

If this project is not currently licensed, specify the intended license before publishing the repository.

---

## About resQ

resQ is built with the goal of making stray animal and bird rescue operations faster, more organized, and easier to coordinate.

> Report. Respond. Rescue.
