FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline
COPY src src
RUN ./mvnw clean package -DskipTests
CMD ["java", "-jar", "target/portfolio-backend-1.0.0.jar"]
