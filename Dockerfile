# First stage: Build the application
FROM maven:3.9.9-eclipse-temurin-23 AS build
WORKDIR /app

# Copy only necessary files first to leverage Docker caching
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Second stage: Run the application
FROM eclipse-temurin:23-jdk
WORKDIR /app

# Copy only the built JAR from the previous stage
COPY --from=build /app/target/Fetch-take-home-exercise-0.0.1-SNAPSHOT.jar Receipt-processor.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "Receipt-processor.jar"]