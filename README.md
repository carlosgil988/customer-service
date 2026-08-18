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
Java 21
Spring Boot 4
Spring Web
Spring Data JPA
Maven
H2
JUnit 5
Mockito
OpenAPI 3
OpenAPI Generator
Swagger UI
Testing

The project is developed following a TDD approach, using RED → GREEN cycles to incrementally implement the application.

Tests cover the main application use cases and persistence/integration behaviour.

The project also uses H2 for integration testing with a real JPA persistence layer.

Current API

The service currently exposes the customer retrieval endpoint defined by the OpenAPI contract:

GET /customers/{id}

Swagger UI is available when running the application:

http://localhost:8080/api-docs/index.html
Project Goals

The main goal of this project is to practise and demonstrate:

Hexagonal Architecture
SOLID principles
TDD
Contract-first API development
Separation of domain and infrastructure concerns
Spring Boot application design
Persistence with JPA
Integration testing
API documentation with OpenAPI and Swagger UI

The project is being developed incrementally, with functionality and tests added through small, isolated commits.