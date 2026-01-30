# --------------------------------------
# Stage 1 – Build (Jar Builder)
# --------------------------------------

# Modern Maven + JDK image
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom.xml first for dependency caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build application (skip tests)
RUN mvn clean package -DskipTests


# --------------------------------------
# Stage 2 – Runtime (Lightweight Image)
# --------------------------------------

# Small, secure JRE image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy only the built jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose application port
EXPOSE 8085

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
