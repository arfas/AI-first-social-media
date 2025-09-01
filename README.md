# AI-First Social Platform MVP

This project is a multi-module Spring Boot application that serves as a Minimum Viable Product (MVP) for an AI-First Social Platform.

## Modules

The project is divided into the following modules:

-   `user-service`: Handles user registration, login, and profile management. It uses JWT for authentication.
-   `content-service`: Manages content creation (text, image, video), storage, and retrieval. It also handles social interactions like likes and comments.
-   `feed-service`: Generates a personalized feed for users, currently based on recency and followed users.
-   `api-gateway`: A single entry point to the system, built with Spring Cloud Gateway. It routes requests to the appropriate services and provides centralized JWT validation and aggregated API documentation.

## Tech Stack

-   Java 21
-   Spring Boot 3
-   Maven
-   PostgreSQL
-   Flyway for database migrations
-   Docker & Docker Compose
-   JWT for authentication
-   Spring Cloud Gateway
-   OpenAPI/Swagger for API documentation

## How to Run the Application

To run the application, you will need to have Docker and Docker Compose installed on your system.

1.  **Clone the repository** (if you haven't already).

2.  **Build and run the application with Docker Compose.**
    Open a terminal in the root directory of the project and run the following command:

    ```bash
    docker compose up --build -d
    ```

    This command will:
    -   Build the Docker images for each of the services.
    -   Start containers for PostgreSQL and all the application services.
    -   The `-d` flag runs the containers in detached mode.

3.  **Accessing the services:**
    Once the containers are up and running, the services will be available at the following ports on your local machine:
    -   **API Gateway:** `http://localhost:8083`
    -   **User Service:** `http://localhost:8080`
    -   **Content Service:** `http://localhost:8081`
    -   **Feed Service:** `http://localhost:8082`

4.  **Using the API:**
    All API requests should be made through the API Gateway. The gateway provides an aggregated Swagger UI where you can explore and interact with all the API endpoints from the different services.

    -   **Swagger UI:** `http://localhost:8083/swagger-ui.html`

    From the Swagger UI, you can test the entire application flow, from signing up a new user to creating content and viewing the feed.
