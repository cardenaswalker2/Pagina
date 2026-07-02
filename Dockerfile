# Build stage
FROM maven:3.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/platform-0.0.1-SNAPSHOT.jar platform.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","platform.jar"]
