# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project JAR file into the container
COPY target/PlayerAPI-0.0.1-SNAPSHOT.jar /app/app.jar
COPY src/main/resources/player.csv src/main/resources/player.csv

# Expose the default port Spring Boot runs on (8080)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
