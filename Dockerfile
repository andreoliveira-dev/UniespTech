# Stage 1: Build da aplicação (Compilação)
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

COPY SistemaUniesp.java .

RUN javac SistemaUniesp.java

# Stage 2: Imagem final de execução (Runtime)
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/SistemaUniesp.class .

ENTRYPOINT ["java", "SistemaUniesp"]
