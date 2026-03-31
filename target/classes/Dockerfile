# ============================================================
# Estágio 1 — build
# Compila o projeto e gera o fat jar dentro de um container
# temporário, sem precisar ter Maven instalado na máquina.
# ============================================================
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o pom.xml primeiro para aproveitar o cache do Docker.
# Se o pom.xml não mudar, o Maven não baixa as dependências de novo.
COPY pom.xml .
RUN mvn dependency:go-offline --no-transfer-progress

# Copia o restante do código e gera o jar
COPY src/ ./src/
COPY SistemaUniesp.java .
RUN mvn package --no-transfer-progress -DskipTests

# ============================================================
# Estágio 2 — imagem final
# Copia apenas o jar gerado, descartando Maven, código-fonte
# e dependências de build. Resultado: imagem muito menor.
# ============================================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia somente o fat jar do estágio anterior
COPY --from=build /app/target/uniesp-tech.jar uniesp-tech.jar

# Variáveis de ambiente — devem ser fornecidas ao rodar o container.
# Exemplo: docker run -e DB_URL=... -e DB_USUARIO=... -e DB_SENHA=...
ENV DB_URL=""
ENV DB_USUARIO=""
ENV DB_SENHA=""

# Executa o sistema
ENTRYPOINT ["java", "-jar", "uniesp-tech.jar"]