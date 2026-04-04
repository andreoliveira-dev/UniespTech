# Projeto Uniesp Tech: Sistema de Gestão Acadêmica Escalonável

## Situação-Problema

A **Uniesp Tech** herdou um sistema de uma startup que faliu. O código atual é funcional, porém extremamente **frágil**:

- **Dados Voláteis:** Armazena dados apenas em memória (perde tudo ao reiniciar).
- **Sem Qualidade:** Não possui nenhum teste unitário ou de integração.
- **Deploy Artesanal:** O processo é manual (copiar o `.jar` via FTP).
- **Blindness (Cegueira):** Ninguém sabe se o sistema está online ou offline até que um usuário reclame.

---

## O Objetivo

Em **3 semanas**, reconstruir a base deste sistema, aplicar persistência real, containerizar a aplicação e criar um fluxo de CI/CD profissional que impeça bugs de chegarem em produção.

---

## Tecnologias Utilizadas

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Banco de dados | PostgreSQL |
| Logs | SLF4J + Logback |
| Testes | JUnit 5 |
| Build | Maven 3.9 |
| Container | Docker (multi-stage build) |
| CI | GitHub Actions |
| CD | Render |

---

## Arquitetura

O sistema foi refatorado seguindo o padrão de camadas:

```
src/main/java/
├── SistemaUniesp.java       ← Ponto de entrada
├── config/
│   └── ConfiguracaoBanco.java
├── controller/
│   └── AlunoController.java
├── model/
│   └── Aluno.java
├── repository/
│   ├── AlunoRepository.java
│   ├── AlunoRepositoryMemoria.java
│   └── AlunoRepositoryPostgres.java
├── service/
│   └── AlunoService.java
└── health/
    └── HealthCheckServer.java
```

---

## Como Rodar Localmente

### Pré-requisitos

- Java 21
- Maven 3.9+
- PostgreSQL rodando localmente

### 1. Clone o repositório

```bash
git clone https://github.com/andreoliveira-dev/UniespTech.git
cd UniespTech
```

### 2. Configure as variáveis de ambiente

```bash
# Windows CMD
set DB_URL=jdbc:postgresql://localhost:5432/uniesptech
set DB_USER=postgres
set DB_PASS=sua_senha

# Linux/Mac
export DB_URL=jdbc:postgresql://localhost:5432/uniesptech
export DB_USER=postgres
export DB_PASS=sua_senha
```

### 3. Compile e rode

```bash
mvn clean package -DskipTests
java -jar target/uniesp-tech.jar
```

### 4. Rode os testes

```bash
mvn test
```

---

## Como Rodar com Docker

```bash
docker build -t uniesp-tech .

docker run -p 8080:8080 \
  -e DB_URL=jdbc:postgresql://host:5432/uniesptech \
  -e DB_USER=postgres \
  -e DB_PASS=sua_senha \
  uniesp-tech
```

---

## CI/CD

### Continuous Integration — GitHub Actions

A cada push ou Pull Request na branch `main` ou `feat/**`, o pipeline executa automaticamente:

1. Compila o projeto com Maven
2. Roda os 13 testes unitários
3. Publica o relatório de testes como artifact
4. Gera a imagem Docker e envia para o Docker Hub
5. Gera o `.jar` versionado com o hash do commit

### Continuous Deployment — Render

A cada push na branch configurada, o Render faz o deploy automático da aplicação usando o `Dockerfile` do repositório.

**URL de produção:** https://uniesptech-je1d.onrender.com

---

## Health Check

A aplicação expõe um endpoint de monitoramento em `/health` que verifica a saúde da aplicação e da conexão com o banco de dados.

**Endpoint:** `GET /health`

### Resposta — Sistema saudável (HTTP 200)

```json
{
  "timestamp": "2026-04-04T12:25:10.788279438",
  "app": "UP",
  "database": "UP",
  "status": "UP"
}
```

### Resposta — Banco fora do ar (HTTP 503)

```json
{
  "timestamp": "2026-04-04T12:31:54.945966008",
  "app": "UP",
  "database": "DOWN - Connection refused",
  "status": "DOWN"
}
```

---

## Evidências de Monitoramento

### Testes Unitários — 13 testes, 0 falhas

```
Tests run: 13, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Cobertura dos testes:

| Suite | Testes |
|---|---|
| `ValidacoesNome` | Nome vazio, nulo, com números |
| `ValidacoesCpf` | CPF vazio, nulo, com letras, curto, longo |
| `CadastroSucesso` | Cadastro válido, cadastro e listagem |
| `ListagemDelecao` | Lista vazia, deletar todos, recadastrar após deleção |

### Logs SLF4J em produção

```
INFO  service.AlunoService - Tentativa de cadastro — nome: 'João Silva'
INFO  service.AlunoService - Aluno cadastrado com sucesso — ID: 0, nome: 'João Silva'
WARN  service.AlunoService - Cadastro rejeitado — CPF com tamanho inválido: 10 dígitos
WARN  service.AlunoService - ATENÇÃO — Todos os alunos foram deletados do sistema!
```

### Chaos Test — Simulação de queda do banco

O banco foi suspenso no Render para simular uma falha em produção. A aplicação detectou a indisponibilidade imediatamente e respondeu com HTTP 503:

```json
{
  "app": "UP",
  "database": "DOWN - Unable to connect to database",
  "status": "DOWN"
}
```

Após restaurar o banco, o sistema voltou ao normal automaticamente sem necessidade de restart:

```json
{
  "app": "UP",
  "database": "UP",
  "status": "UP"
}
```

---

## Semana 4: Prova Prática

Aplicação de uma *Hotfix de Emergência* em tempo real para avaliar o domínio sobre o fluxo DevOps construído.
