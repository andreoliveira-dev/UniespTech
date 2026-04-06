# Stage 1: Build da aplicação (Compilação)
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

# Copia o código e o driver para o estágio de build
COPY SistemaUniesp.java .
COPY postgresql-42.7.2.jar .

# Compila o Java avisando que o driver existe (Classpath)
RUN javac -cp .:postgresql-42.7.2.jar SistemaUniesp.java

# Stage 2: Imagem final de execução (Runtime)
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia o arquivo compilado e o driver para a imagem final
COPY --from=builder /app/SistemaUniesp.class .
COPY --from=builder /app/postgresql-42.7.2.jar .

# Comando para rodar incluindo o driver no Classpath
ENTRYPOINT ["java", "-cp", ".:postgresql-42.7.2.jar", "SistemaUniesp"]
