
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY UniespTech-feat-LuanaJessica/UniespTech-Luana .

RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]