# 🎓 Sistema Uniesp

Aplicação Java desenvolvida como parte do desafio acadêmico, com foco em boas práticas de desenvolvimento, persistência de dados, containerização e deploy em nuvem.

---

## 🚀 Tecnologias utilizadas

*  Java 21
*  Maven
*  H2 Database
*  SparkJava
*  Docker
*  GitHub Actions (CI)
*  Render (Deploy)

---

## 📌 Funcionalidades

* ✅ Cadastro de alunos
* ✅ Listagem de alunos
* ✅ Persistência em banco de dados (H2 em arquivo)
* ✅ Endpoint de Health Check
* ✅ Logs estruturados da aplicação

---

## 🌍 Aplicação em produção

🔗 Acesse aqui:
https://uniesp-app.onrender.com

---

## 🧪 Endpoints disponíveis

### 🔍 Health Check

GET /health

Resposta:
OK

---

### ➕ Cadastrar aluno

GET /cadastrar?nome=Nome&cpf=12345678900

Exemplo:
GET /cadastrar?nome=Duda&cpf=12345678900

---

### 📋 Listar alunos

GET /alunos

---

## 🐳 Como rodar com Docker

### 1. Build da imagem

docker build -t uniesp-app .

### 2. Rodar o container

docker run -p 4567:4567 uniesp-app

---

## ⚙️ CI/CD

O projeto possui integração contínua com GitHub Actions:

* ✔ Build automático a cada push
* ✔ Execução de testes
* ✔ Geração do artefato (.jar)
* ✔ Pronto para deploy contínuo

---

## 🧪 Teste de Chaos Engineering

Foi simulado um cenário de falha na conexão com o banco de dados.

📌 Resultado:

* A aplicação continuou rodando
* Erro registrado nos logs
* Sistema manteve estabilidade

---

## 📊 Monitoramento

* Logs implementados com SLF4J
* Registro de operações críticas (cadastro, erros)
* Health check disponível para verificação do estado da aplicação

---


## 📌 Status do projeto

✅ Finalizado
