# QuizService

This is a Spring Boot microservice responsible for managing quizzes within a larger microservices-based application.

## Key Functionalities

- **Create and Manage Quizzes**: The service exposes REST endpoints to create new quizzes and retrieve existing ones.
- **Integration with `QuestionService`**: It communicates with a separate `QuestionService` to fetch questions for each quiz. This demonstrates inter-service communication in a microservices architecture.
- **Service Discovery**: It's configured as a Eureka client, meaning it registers itself with a Eureka service registry. This allows other services in the system to find and interact with it without hardcoding its location.

## Technical Details

- **Frameworks**: Built with the Spring Boot framework.
- **Database**: Uses Spring Data JPA to interact with a MySQL database for storing quiz data.
- **Communication**:
    - **REST API**: Exposes a RESTful API for quiz management.
    - **OpenFeign**: Uses Spring Cloud OpenFeign for declarative, type-safe communication with the `QuestionService`. The `QuestionClient` interface defines how to fetch questions for a specific quiz.

## Code Structure

- **`QuizController`**: Handles incoming HTTP requests for creating and getting quizzes.
- **`QuizService` / `QuizServiceImpl`**: Contains the core business logic, including the logic to fetch questions from the `QuestionService` and combine them with the quiz data.
- **`QuizRepository`**: A Spring Data JPA repository for database operations on the `Quiz` entity.
- **`Quiz` Entity**: Represents the quiz data model. It includes a `transient` list of `Question` objects, which are populated at runtime by calling the `QuestionService`.
- **`QuestionClient`**: A Feign client interface that defines the contract for communicating with the `QuestionService`.
