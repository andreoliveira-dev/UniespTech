package Service;

import Model.Aluno;
import Repository.AlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class AlunoService {

    private static final Logger logger = LoggerFactory.getLogger(AlunoService.class);
    private AlunoRepository repository = new AlunoRepository();

    public boolean cadastrarAluno(String nome, String cpf) {
        logger.info("📝 Tentativa de cadastro - Nome: {}, CPF: {}", nome, cpf);

        if (nome == null || nome.isBlank()) {
            logger.warn("❌ Falha no cadastro - Nome vazio");
            System.out.println("Não pode ser vazio ");
            return false;
        }

        if (!cpf.matches("\\d{11}")) {
            logger.warn("❌ Falha no cadastro - CPF inválido: {}", cpf);
            System.out.println("CPF deve ter 11 números");
            return false;
        }

        try {
            Aluno aluno = new Aluno(nome, cpf);
            repository.cadastrar(aluno);
            logger.info("✅ Aluno cadastrado com sucesso - Nome: {}, CPF: {}", nome, cpf);
            return true;
        } catch (Exception e) {
            logger.error("💥 Erro crítico ao cadastrar aluno - Nome: {}, CPF: {}", nome, cpf, e);
            return false;
        }
    }

    public List<Aluno> listarAlunos() {
        logger.debug("📋 Listando todos os alunos");
        List<Aluno> alunos = repository.listar();
        logger.info("📊 Total de alunos listados: {}", alunos.size());
        return alunos;
    }

    public void deletarTodos() {
        logger.warn("🗑️ Iniciando deleção de todos os alunos");
        repository.deletartodos();
        logger.info("✅ Todos os alunos foram deletados");
    }
}