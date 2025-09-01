# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY user-service/pom.xml ./user-service/
COPY content-service/pom.xml ./content-service/
COPY feed-service/pom.xml ./feed-service/
COPY api-gateway/pom.xml ./api-gateway/
RUN mvn dependency:go-offline -B -f pom.xml

COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM openjdk:21-slim
WORKDIR /app
ARG SERVICE_NAME
COPY --from=build /app/${SERVICE_NAME}/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
