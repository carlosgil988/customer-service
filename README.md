Customer Service

Backend microservice for customer management, developed with Java and Spring Boot as a practical project focused on clean architecture, Test-Driven Development and API contract-first development.

Architecture

The project follows a Hexagonal Architecture approach, separating the core business logic from external infrastructure and delivery mechanisms.

customer-service
├── domain
│   ├── model
│   └── exception
├── application
│   ├── service
│   └── port
└── infrastructure
├── controller
├── mapper
└── persistence

The application is organized around:

Domain — business models and domain exceptions.
Application — use cases and ports.
Infrastructure — HTTP controllers, persistence adapters and JPA implementations.
API Contract

The API contract is maintained separately in the customer-api project.

The contract is defined using OpenAPI 3 and is used to generate the API interfaces and models consumed by this service.

This keeps the API definition independent from the service implementation.

Technologies

* Java 21
* Spring Boot 4
* Spring Web
* Spring Data JPA
* Maven
* Maven Wrapper
* H2
* JUnit 5
* Mockito
* OpenAPI 3
* OpenAPI Generator
* Swagger UI
* Docker
* JaCoCo
* Maven Failsafe

Docker

The application can be built and executed as a Docker container using a multi-stage Docker build.

The build stage uses Java 21 and Maven to compile the application, while the runtime stage uses a lightweight Java 21 JRE image to run the generated application JAR.

Build the Docker image:

docker build -t customer-service .

Run the container:

docker run --name customer-service -p 8080:8080 customer-service

The application will then be available at:

http://localhost:8080

Docker provides a reproducible Java 21 runtime environment independently of the Java version installed on the host machine.

Testing

The project is developed following a TDD approach, using RED → GREEN cycles to incrementally implement the application.

Testing includes unit tests and integration tests.

Unit tests use JUnit 5 and Mockito.

Integration tests use Spring Boot, H2 and a real JPA persistence layer to verify persistence behaviour.

Maven Failsafe is used to execute integration tests during the Maven verify lifecycle.

JaCoCo is used to enforce a minimum instruction coverage of 80%.

Current instruction coverage: 91%

Current API

The service currently exposes the customer retrieval endpoint defined by the OpenAPI contract:

GET /customer/{id}

Example:

GET http://localhost:8080/customer/1

Swagger UI is available when running the application:

http://localhost:8080/api-docs/index.html

Build & Verification

The complete verification process can be executed using the Maven Wrapper:

./mvnw clean verify

The Maven Wrapper ensures that the project uses the Maven version defined by the project rather than relying on a globally installed Maven version.

Project Goals

The main goal of this project is to practise and demonstrate:

* Hexagonal Architecture
* SOLID principles
* TDD
* Contract-first API development
* Separation of domain and infrastructure concerns
* Spring Boot application design
* Persistence with JPA
* Unit and integration testing
* Code coverage verification with JaCoCo
* API documentation with OpenAPI and Swagger UI
* Containerization with Docker
* Reproducible application builds

The project is being developed incrementally, with functionality and tests added through small, isolated commits.