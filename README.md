# 🎓 Uniesp Tech: Sistema de Gestão Acadêmica Escalonável

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)

## 📌 Sobre o Projeto

O **Uniesp Tech** nasceu do desafio de resgatar e modernizar um sistema herdado de uma startup. O sistema original possuía um código funcional, porém extremamente frágil: dados armazenados apenas em memória, ausência de testes, deploy manual via FTP e falta de monitoramento (blindness).

O objetivo deste projeto foi reconstruir a base do sistema em **3 semanas**, aplicando persistência real, containerização e criando um fluxo de CI/CD profissional para garantir a qualidade do software em produção.

---

## 🌐 Demonstração Online (Deploy)

A aplicação está rodando em ambiente Cloud e pode ser acessada através do link abaixo:

🚀 **[Acessar API no Render](https://uniesptech-w60w.onrender.com/alunos)**

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java
* **Gerenciador de Dependências:** Maven
* **Testes:** JUnit 5
* **Banco de Dados:** H2 Database / PostgreSQL
* **Containerização:** Docker (Multi-stage build)
* **CI/CD:** GitHub Actions
* **Deploy Cloud:** [Render] **
* **Logs e Monitoramento:** SLF4J / Log4j

---

## 🚀 Evolução e Cronograma (Roadmap)

O desenvolvimento foi dividido em etapas focadas na cultura DevOps e na qualidade de software:

### ✅ Semana 1: Refatoração, Qualidade e Governança
* **Refatoração Arquitetural:** Separação do código "macarrônico" em camadas (Controller, Service, Model, e Repository).
* **Validações de Negócio:** Implementação de regras rigorosas (ex: validação de CPF e campos obrigatórios).
* **Maven:** Configuração do `pom.xml` para gestão do ciclo de vida e dependências.
* **Testes Unitários:** Criação de suíte de testes com JUnit 5.
* **Governança e Fluxo Git:** Adoção de Kanban (GitHub Projects), bloqueio de push direto na `main` e uso obrigatório de Pull Requests com Code Review.

### ✅ Semana 2: Persistência e Containerização
* **Persistência de Dados:** Substituição de dados voláteis (`ArrayList`) por um banco de dados real.
* **Docker:** Criação de um `Dockerfile` otimizado com *Multi-stage build* para gerar imagens leves.
* **Continuous Integration (CI):** Configuração de pipeline no GitHub Actions para rodar build e testes automaticamente a cada push/PR.
* **Artefatos:** Geração e versionamento de imagens Docker/.jar no GitHub.

### ✅ Semana 3: Cloud, Deploy e Monitoramento
* **Health Check:** Implementação de rota `/health` para monitorar a saúde da aplicação e conexão com o banco.
* **Logging Estruturado:** Configuração de logs para auditoria e captura de erros críticos.
* **Continuous Deployment (CD):** Deploy automatizado configurado em ambiente Cloud.
* **Engenharia do Caos (Chaos Test):** Simulação de quedas do banco de dados para validar a resiliência e o registro de logs.

### 🚨 Semana 4: Prova Prática (Hotfix de Emergência)

Nesta etapa, foi realizada uma simulação de falha crítica em ambiente de produção para testar a resiliência do pipeline e a agilidade na recuperação do sistema (MTTR).

#### 1. O Incidente (Simulação de Erro)
Para quebrar a aplicação, foram introduzidos propositalmente dois erros críticos:
* **Erro de Sintaxe:** Inserção de caracteres inválidos (`asdaf`) na classe `GestaoApplication.java`, o que impede a compilação do projeto.
* **Erro de Infraestrutura:** Alteração da URL do banco de dados no `application.properties` para um endpoint inexistente (`jdbc:h2:tcp://localhost:9999/banco-quebrado`), garantindo falha na inicialização do contexto do Spring.

#### 2. O Fluxo de Resolução (DevOps Workflow)
A correção não foi feita diretamente na branch principal. O protocolo seguido foi:
1.  **Isolamento:** Criação de uma branch temporária `hotfix/emergency-repair`.
2.  **Continuous Integration (CI):** O pipeline de build no GitHub Actions barrou a tentativa de merge inicial, identificando o erro de compilação automaticamente.
3.  **Remediação:** Correção dos arquivos e validação através de testes unitários locais (`mvn test`).
4.  **Continuous Deployment (CD):** Após o merge do Pull Request aprovado pelo CI, o Render realizou o redeploy automático da versão estável.

#### 3. Resultados
* **MTTR (Mean Time To Recovery):** Reduzido devido à automação do deploy.
* **Integridade:** O sistema de build garantiu que nenhum código "quebrado" permanecesse em produção.
---

## ⚙️ Como Executar o Projeto Localmente

### Pré-requisitos
* Java 11 ou superior
* Maven
* Docker (Opcional, mas recomendado)

### Passo a passo

1. **Clone o repositório:**

git clone [https://github.com/JOAOVICTORSOARESDELIMA/UniespTech.git](https://github.com/JOAOVICTORSOARESDELIMA/UniespTech.git)
cd UniespTech

2. **Mude para a branch correta (se necessário):**

git checkout Joao-Victor-Soares

3. **Para rodar usando Maven:**

   mvn clean install
   mvn spring-boot:run 

4. **Para rodar usando Docker:

# Fazer o build da imagem
docker build -t uniesp-tech-app .

# Rodar o container
docker run -p 8080:8080 uniesp-tech-app

# 🔍 Monitoramento e Endpoints Úteis
Após a aplicação estar rodando, você pode acessar:

Health Check: http://localhost:8080/health - Retorna o status de saúde da aplicação.

