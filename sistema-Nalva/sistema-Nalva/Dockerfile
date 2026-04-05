# Stage 1: Build da aplicação (Compilação)
FROM eclipse-temurin:17-jdk-alpine AS builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o código-fonte para o diretório de trabalho
COPY SistemaUniesp.java .

# Compila o arquivo Java, gerando o bytecode (SistemaUniesp.class)
RUN javac SistemaUniesp.java

# Stage 2: Imagem final de execução (Runtime)
FROM eclipse-temurin:17-jre-alpine

# Define o diretório de trabalho final
WORKDIR /app

# Copia apenas o arquivo compilado (.class) do estágio "builder" anterior
COPY --from=builder /app/SistemaUniesp.class .

# Comando padrão a ser executado quando o container iniciar
# É importante executar a aplicação em modo interativo caso seja utilizado interativamente,
# por exemplo: docker run -it nome-da-imagem
ENTRYPOINT ["java", "SistemaUniesp"]