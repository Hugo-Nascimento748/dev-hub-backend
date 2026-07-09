# Build
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Run
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Porta
EXPOSE 8081

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]