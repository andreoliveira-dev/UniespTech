import static spark.Spark.*;
import service.AlunoService;

public class SistemaUniesp {

    public static void main(String[] args) {

        AlunoService service = new AlunoService();

        String portStr = System.getenv("PORT");

        if (portStr != null) {
            port(Integer.parseInt(portStr));
        } else {
            port(4567);
        }

        // Health
        get("/health", (req, res) -> "OK");

        // Cadastro
        get("/cadastrar", (req, res) -> {
            String nome = req.queryParams("nome");
            String cpf = req.queryParams("cpf");

            boolean resultado = service.cadastrarAluno(nome, cpf);

            if (resultado) {
                return "Aluno cadastrado com sucesso!!!";
            } else {
                return "Erro ao cadastrar aluno";
            }
        });

        // Listar
        get("/alunos", (req, res) -> service.listarAlunos().toString());
    }
}