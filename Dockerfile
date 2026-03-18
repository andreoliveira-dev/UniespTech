# ============================================================
# STAGE 1 — BUILD
# Compila o projeto e gera o fat jar com Maven
# ============================================================
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copia o pom.xml separado para aproveitar o cache de dependências.
# Se o pom não mudar, o Docker reutiliza a camada e pula o download.
COPY pom.xml .
RUN mvn dependency:go-offline -q

# Copia o restante do código e compila
COPY src ./src
COPY SistemaUniesp.java .
RUN mvn package -q

# ============================================================
# STAGE 2 — RUNTIME
# Imagem final enxuta, apenas com o JRE e o .jar gerado
# ============================================================
FROM eclipse-temurin:21-jre-alpine AS runtime

WORKDIR /app

# Copia apenas o fat jar do stage anterior
COPY --from=build /app/target/uniesp-tech.jar uniesp-tech.jar

# Variáveis de ambiente para conexão com o PostgreSQL.
# Sobrescreva ao rodar o container: -e DB_URL=... -e DB_USUARIO=... -e DB_SENHA=...
ENV DB_URL=jdbc:postgresql://postgres:5432/uniesp \
    DB_USUARIO=postgres \
    DB_SENHA=1404

ENTRYPOINT ["java", "-jar", "uniesp-tech.jar"]