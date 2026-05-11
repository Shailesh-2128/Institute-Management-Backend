# Use Java 21 image
FROM eclipse-temurin:21

# Set working directory
WORKDIR /app

# Copy jar file
COPY target/*.jar app.jar

# Expose application port
EXPOSE 8085

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]