# 🎓 Uniesp Tech - Sistema de Gestão Acadêmica

Sistema de gestão acadêmica desenvolvido para a disciplina de DevOps. O projeto aplica boas práticas de qualidade, containerização, integração contínua e monitoramento.

## 🚀 Tecnologias Utilizadas
- **Java 21**
- **H2 Database** (persistência em arquivo)
- **Maven** (gerenciamento de dependências e build)
- **Docker** (containerização)
- **GitHub Actions** (CI/CD)
- **SLF4J** (logs estruturados)
- **Render** (deploy em nuvem)

## 🌐 Aplicação em Produção
- **URL Principal:** [https://uniesp-tech-academico.onrender.com](https://uniesp-tech-academico.onrender.com)
- **Health Check:** [https://uniesp-tech-academico.onrender.com/health](https://uniesp-tech-academico.onrender.com/health)

## ✅ Funcionalidades
- Cadastro de alunos com validação de CPF (11 dígitos) e nome não vazio.
- Listagem de alunos cadastrados.
- Remoção em massa de todos os registros.
- Persistência dos dados em banco H2.
- Endpoint de **health check** (`/health`) para monitoramento.
- **Logs estruturados** com níveis (INFO, WARN, ERROR) e saída em arquivo.
- Pipeline **CI/CD** automatizada com GitHub Actions.
- **Deploy contínuo** no Render via Docker.

## 📸 Evidências de Funcionamento

### Servidor Online (https://prnt.sc/NQPk-5vA74Wl)

### Health Check(https://prnt.sc/NQPk-5vA74Wl)

### Pipeline CI/CD(https://prnt.sc/X5Tsnp9vwJ7m)

## 🧪 Chaos Test: Simulação de Queda do Banco

O **Chaos Test** valida a resiliência da aplicação, verificando se o health check detecta corretamente a indisponibilidade do banco de dados.

### Como Reproduzir o Teste

#### Opção 1 – Simular falha na string de conexão (mais simples)

1. Acesse o arquivo `src/main/java/Config/DatabaseConnection.java`.
2. Altere a URL do banco para um valor inválido:
   ```java
   private static final String URL = "jdbc:h2:./banco_inexistente";

RETORNO JSON
{
"status": "DOWN",
"database": "H2",
"error": "Connection failed or invalid"
}

👨‍💻 Autor
Lucas Accioly

GitHub: @lucasaccioly
