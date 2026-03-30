
# 🚀 UniespTech - Sistema Barões da Pisadinha(inicialmente java 17)
> **Status do Projeto:** 🟢 CI/CD Voando | **Motor:** Java 22 (GraalVM)

---

## 📝 Diário de Bordo: O que eu aprontei , estressei e chorei

### 🗓️ Semana 1: O Começo (Sem Enrolação)
* **O Motor:** Fui logo de **Java 22 (GraalVM)**. É o que tem de mais moderno pro sistema voar e não engasgar na mão do professor.
* **O Setup:** Montei o esqueleto com `controller`, `model`, `repository` e `service` focado no **Usuario**.
* **Banco de Dados:** Até pensei no MySQL Workbench, mas o **H2** ganhou meu coração por ser prático e já vir no "pacote" sem eu precisar instalar 500 coisas e passar raiva.

### 🗓️ Semana 2: O Recheio (Entidade de Vergonha)
* **Foco no Usuario:** Por enquanto foquei em deixar o **Usuario (Aluno/Professor)** 100% funcional. Nada de sair criando 10 tabelas e não fazer nenhuma que preste.
* **Caso o projeto necessite e só por**, eu aumento o leque depois, o importante agora é o motor rodar liso.
* **CRUD Completo:** Fiz o sistema salvar, listar, **atualizar o vizu (Update)** e o **Sniper (Delete)** pra apagar quem não deve estar lá.

### 🗓️ Semana 3: A Blindagem e o Drama do CI/CD
> **Onde o B.O. aconteceu e o filho chorou!** Eu montei o `ci.yml` toda feliz, mas o bicho **brecou feio** logo de cara e eu quase surtei botando a culpa em todos os bandindin possiveis.

* **O Drama do Permission Denied:** O GitHub Actions dava um erro de `Permission Denied`. Eu entrei numa preocupação danada achando que o **Docker** tava quebrado, que a imagem tava bichada ou que o container não ia subir nunca. Fiquei achando que eu tinha feito besteira no Dockerfile e que nada ia fununciar porque o Docker não queria ler meus arquivos.
* **A Realidade:** No fim, o Docker era inocente e eu sofrendo de graça! O culpado era o **`mvnw`** (Maven Wrapper). Como eu subi o código do Windows, o Linux do GitHub não tinha permissão pra "executar" o arquivo. Ele travava o build antes mesmo de começar.
* **Como eu ajeitei:** Tive que dar um "alvará" pro arquivo. Coloquei o comando mágico `run: chmod +x mvnw` no arquivo de CI. Assim que o Linux ganhou permissão, o build destravou e o Actions ficou **verdinho** na hora. 🚀(O que importa n e a gambiarra e nem a pesquisa e sim que o baroes voltou a tocar)

---

## 🛠️ Tecnologias & Proteções

* **Anti B.O. (Validation):** Travei o **CPF em 11 números**. Se tiver errado, o fofoqueiro do Log já avisa no console.
* **Health Check (Unimed do App):** Criei a rota `/health` pra ver se o banco H2 capotou ou se o carrossel tá girando.
* **Logs do BBB:** O Service agora fofoca tudo o que acontece (quem entrou, quem mudou o vizu e quem tentou burlar o CPF).

---

## 📡 Como Testar (Insomnia/Postman)

| Rota | Método | O que faz? |
| :--- | :--- | :--- |
| `/usuarios/health` | `GET` | Vê se o sistema e o banco tão vivos |
| `/usuarios/listar` | `GET` | Chama todos os Thunder Cats |
| `/usuarios/cadastrar`| `POST` | Salva o meliante (exige CPF 11 dígitos) |
| `/usuarios/atualizar/{id}` | `PUT` | Muda o vizu do meliante (ID) |
| `/usuarios/deletar/{id}` | `DELETE` | O Sniper entra em ação |

---

## 🐳 Rodando via Docker (O CD em ação!)

Como o meu CI/CD já faz o trabalho sujo de buildar e mandar pro GitHub Packages, é só rodar:

```bash
# Puxando a imagem que eu gerei
docker pull ghcr.io/SEU_USER_GITHUB/uniesptech:latest

# Rodando o carrossel na porta 8080
docker run -p 8080:8080 ghcr.io/SEU_USER_GITHUB/uniesptech:latest
```

---

### 🏁 Status Final:
O CI/CD tá voando e o Docker tá criando a imagem sozinho no GHCR. O sistema tá blindado, automatizado e, se o professor quiser mais tabela, **caso o projeto necessite e só por**.
