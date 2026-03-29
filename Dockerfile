# ETAPA 1: BUILD (Compila o código)
FROM ghcr.io/graalvm/jdk-community:22 AS build
LABEL authors=" Francielly Ariel Brasil"
WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Dá permissão para o Maven Wrapper e compila o JAR
# Permixions de compileixon pra o querido daqui de baixo oq ta escrito run
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# ETAPA 2: RUNTIME (Executa a aplicação)
FROM ghcr.io/graalvm/jdk-community:22
WORKDIR /app

# Criamos a pasta onde o H2 vai salvar os arquivos
RUN mkdir ./data

# Copia apenas o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta do Spring
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]