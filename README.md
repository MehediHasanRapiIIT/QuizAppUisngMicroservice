# Quiz App using Microservices

This project is a quiz application built using a microservices architecture. It consists of four main services that work together to provide the quiz functionality.

## Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Cloud**
  - **Netflix Eureka**: For service discovery and registration.
  - **Spring Cloud Gateway**: As the API Gateway.
  - **OpenFeign**: For declarative REST API communication between services.
- **Spring Data JPA**: For database interaction.
- **MySQL**: As the relational database.
- **Lombok**: To reduce boilerplate code.
- **Maven**: For project dependency management.

## Getting Started

To run this project, you will need to have Java 17 and Maven installed. You will also need a running instance of MySQL.

1.  **Configure `application.properties`**: For `QuestionService` and `QuizService`, you will need to configure the `application.properties` file with your MySQL database credentials.
2.  **Start the services**: Start the services in the following order:
    1.  `ServiceRegistry`
    2.  `ApiGateway`
    3.  `QuestionService`
    4.  `QuizService`

    For each service, navigate to its root directory and run the following command:

    ```bash
    mvn spring-boot:run
    ```

## Services

### 1. Service Registry

- **Directory**: `ServiceRegistry`
- **Description**: This is a Spring Cloud Netflix Eureka server. It acts as a central registry where all other microservices register themselves. This allows for dynamic service discovery, enabling services to find and communicate with each other without needing to hardcode network locations.
- **Port**: 8761 (default for Eureka)

### 2. API Gateway

- **Directory**: `ApiGateway`
- **Description**: The API Gateway is the single entry point for all client requests. It's built using Spring Cloud Gateway and is responsible for routing incoming requests to the appropriate microservice. It also integrates with the Service Registry to dynamically discover the locations of the other services.
- **Port**: 8080 (default)

### 3. Question Service

- **Directory**: `QuestionService`
- **Description**: This service is responsible for managing the questions for the quizzes. It provides a REST API for creating, retrieving, and managing questions. It registers itself with the Service Registry so that other services, like the Quiz Service, can discover and interact with it.
- **API Endpoints**:
  - `POST /question`: Creates a new question.
  - `GET /question`: Retrieves all questions.
  - `GET /question/{id}`: Retrieves a specific question by its ID.
  - `GET /question/quiz/{quizId}`: Retrieves all questions for a specific quiz.

### 4. Quiz Service

- **Directory**: `QuizService`
- **Description**: The Quiz Service manages the quizzes themselves. It provides a REST API for creating and retrieving quizzes. When a quiz is requested, this service communicates with the Question Service (using a Feign client) to fetch the relevant questions for that quiz. It also registers with the Service Registry.
- **API Endpoints**:
  - `POST /quiz`: Creates a new quiz.
  - `GET /quiz`: Retrieves all quizzes.
  - `GET /quiz/{id}`: Retrieves a specific quiz by its ID, including its questions.

## Architecture Overview

1.  All microservices (`ApiGateway`, `QuestionService`, `QuizService`) register themselves with the `ServiceRegistry` upon startup.
2.  A client (e.g., a web browser) sends a request to the `ApiGateway`.
3.  The `ApiGateway` looks up the location of the appropriate service (e.g., `QuizService`) in the `ServiceRegistry` and forwards the request.
4.  If the `QuizService` needs data from another service (like questions from the `QuestionService`), it uses its Feign client to make a request to the `QuestionService`, again using the `ServiceRegistry` to find its location.
5.  Each service has its own database (in this case, likely MySQL) to store its data, following the database-per-service pattern.