# AI-First Social Platform MVP

This project is a full-stack application that serves as a Minimum Viable Product (MVP) for an AI-First Social Platform. It includes a multi-module Spring Boot backend and a Next.js frontend.

## Backend

### Modules

The backend is a multi-module Spring Boot application with the following modules:

-   `user-service`: Handles user registration, login, and profile management. It uses JWT for authentication.
-   `content-service`: Manages content creation (text, image, video), storage, and retrieval. It also handles social interactions like likes and comments.
-   `feed-service`: Generates a personalized feed for users, currently based on recency and followed users.
-   `api-gateway`: A single entry point to the system, built with Spring Cloud Gateway. It routes requests to the appropriate services and provides centralized JWT validation and aggregated API documentation.

### Tech Stack

-   Java 21
-   Spring Boot 3
-   Maven
-   PostgreSQL
-   Flyway for database migrations
-   Docker & Docker Compose
-   JWT for authentication
-   Spring Cloud Gateway
-   OpenAPI/Swagger for API documentation

## Frontend

The frontend is a Next.js 14 application.

### Tech Stack

-   Next.js 14
-   React
-   TypeScript
-   TailwindCSS
-   ShadCN UI
-   Axios
-   React Query (TanStack)

## Development

To run the application, you will need to have Docker, Docker Compose, and Node.js installed on your system.

### Running the Entire Application (Backend + Frontend)

The easiest way to run the entire application is with Docker Compose.

1.  **Build and run the application with Docker Compose.**
    Open a terminal in the root directory of the project and run the following command:

    ```bash
    docker compose up --build -d
    ```

    This command will:
    -   Build the Docker images for each of the backend services.
    -   Start containers for PostgreSQL and all the application services.
    -   The `-d` flag runs the containers in detached mode.

2.  **Run the frontend separately.**
    In a new terminal, navigate to the `frontend` directory and run the following commands:

    ```bash
    cd frontend
    npm install
    npm run dev
    ```

3.  **Accessing the application:**
    -   **Frontend:** `http://localhost:3000`
    -   **API Gateway (for backend APIs):** `http://localhost:8083`
    -   **Backend Swagger UI:** `http://localhost:8083/swagger-ui.html`

### Running the Backend Only

If you only want to run the backend services, you can use the `docker-compose.yml` file.

```bash
docker compose up --build -d
```

### Running the Frontend Only

If you have a running instance of the backend, you can run the frontend separately.

```bash
cd frontend
npm install
npm run dev
```

The frontend will be available at `http://localhost:3000`. Make sure the backend API gateway is running at `http://localhost:8083`, as the frontend is configured to make requests to this URL.
