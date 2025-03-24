# Use official maven/Java 21 image with Alpine base to build the application
FROM docker.io/library/eclipse-temurin:21-jre-alpine AS build

# Set the working directory
WORKDIR /app

# Copy the application JAR file
COPY ./target/aider3-0.0.1-SNAPSHOT.jar aider3.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "aider3.jar"]
