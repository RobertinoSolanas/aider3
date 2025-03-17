# Use official maven/Java 21 image with Alpine base to build the application
FROM docker.io/library/maven:3.8.6-openjdk-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use official OpenJDK runtime with Alpine base
FROM docker.io/library/openjdk:21-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the application JAR file
COPY --from=build /app/target/aider3-0.0.1-SNAPSHOT.jar aider3.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "aider3.jar"]
