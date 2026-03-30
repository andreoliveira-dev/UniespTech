package service;

import model.Aluno;
import repository.AlunoRepository;
import java.util.List;

// Alteração: import dos loggers
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlunoService {

    private static final Logger log = LoggerFactory.getLogger(AlunoService.class);

    private AlunoRepository repository = new AlunoRepository();

    public boolean cadastrarAluno(String nome, String cpf) {

        log.info("Tentando cadastrar aluno: nome={}, cpf={}", nome, cpf);

        if (nome == null || nome.isBlank()) {
            log.warn("Falha ao cadastrar: nome vazio");
            return false;
        }

        if (!cpf.matches("\\d{11}")) {
            log.warn("Falha ao cadastrar: CPF inválido -> {}", cpf);
            return false;
        }

        try {
            Aluno aluno = new Aluno(nome, cpf);
            repository.cadastrar(aluno);

            log.info("Aluno cadastrado com sucesso: {}", nome);
            return true;

        } catch (Exception e) {
            log.error("Erro ao cadastrar aluno", e);
            return false;
        }
    }

    public List<Aluno> listarAlunos() {
        log.info("Listando alunos");
        return repository.listar();
    }

    public void deletarTodos() {
        log.warn("Deletando TODOS os alunos");
        repository.deletartodos();
    }
}