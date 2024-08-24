# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and any other required files first
COPY pom.xml .

# Download the dependencies (this will be cached if the pom.xml hasn't changed)
RUN mvn dependency:go-offline -B

# Copy the rest of the application source code
COPY src ./src

# Build the application (this creates the target/ directory with the JAR file)
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY --from=build  /app/target/quizapp-0.0.1-SNAPSHOT.jar /app/quizapp-0.0.1-SNAPSHOT.jar


# Expose the port that your application will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/quizapp-0.0.1-SNAPSHOT.jar"]
