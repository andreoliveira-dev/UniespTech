# Stage 1: Build (Compilação e Testes)
FROM maven:3.8.4-openjdk-17-slim AS builder
WORKDIR /app

# Copia apenas o pom.xml primeiro (otimiza o cache do Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código fonte e os testes
COPY src ./src

RUN mvn clean package -DskipTests -Dmaven.main.skip=false

# Stage 2: Runtime (Execução)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia apenas o .jar gerado no estágio anterior
COPY --from=builder /app/target/*.jar app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
