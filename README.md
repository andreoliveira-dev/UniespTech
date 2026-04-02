# UniespTech - Luana-Jessica

Projeto desenvolvido com **Spring Boot** para gerenciamento de alunos, incluindo cadastro, listagem e remoção, com validações, testes automatizados e deploy em nuvem.

---

# API ONLINE

-> https://uniesptech-1.onrender.com/

---

# Health Check

```http
GET /actuator/health
```

Acesse:
https://uniesptech-1.onrender.com/actuator/health

### Exemplo de resposta:

```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP"
    }
  }
}
```

---

# Funcionalidades

*  Cadastro de aluno
*  Validação de CPF
*  Validação de nome
*  Listagem de alunos
*  Exclusão de todos os alunos
*  Tratamento de exceções
*  Logs de operação

---

# Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Docker
* Docker Compose
* GitHub Actions (CI)
* Render (Deploy Cloud)
* Spring Actuator (Monitoramento)

---

# Como rodar com Docker

```bash
docker-compose up --build
```

A aplicação ficará disponível em:

```text
http://localhost:8080
```

---

# Configuração

Variáveis de ambiente utilizadas:

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://host:5432/banco
SPRING_DATASOURCE_USERNAME=usuario
SPRING_DATASOURCE_PASSWORD=senha
```

---

# Testes

Para rodar os testes:

```bash
mvn test
```

### Cobertura de testes inclui:

* Cadastro com sucesso
* CPF duplicado
* CPF inválido
* Nome vazio
* Listagem
* Exclusão

---

# CI - Integração Contínua

O projeto utiliza **GitHub Actions** para:

* Build da aplicação
* Execução dos testes
* Build da imagem Docker
* Upload de artefatos

---

# CD - Deploy Contínuo

Deploy automático realizado no **Render**, com:

* Banco PostgreSQL em nuvem
* Variáveis de ambiente configuradas
* Monitoramento ativo

---

# Chaos Test

Foi realizado teste de resiliência simulando queda do banco de dados:

*  Banco indisponível → aplicação registra erro nos logs
* Sistema continua estável
* Monitoramento detecta falha

---

# Observabilidade

* Endpoint `/actuator/health`
* Status da aplicação e banco em tempo real
* Logs com níveis:

  * INFO
  * ERROR
  * WARN

---

# Estrutura do Projeto

```
controller/
service/
repository/
dto/
model/
config/
```

---

# Boas Práticas Aplicadas

* Separação de responsabilidades (MVC)
* Uso de DTO
* Injeção de dependência
* Tratamento global de exceções
* Logs estruturados
* Testes unitários com Mockito

---

# Autor

Luana Jessica