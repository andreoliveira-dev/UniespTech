package br.com.uniesp.gestao.service;

import br.com.uniesp.gestao.model.Aluno;
import br.com.uniesp.gestao.repository.AlunoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public void cadastrarAluno(Aluno aluno) {
        log.info("Iniciando tentativa de cadastro para o CPF: {}", aluno.getCpf());

        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            log.error("Falha no cadastro: Nome não pode ser vazio!");
            throw new IllegalArgumentException("Nome não pode ser vazio!");
        }
        if (aluno.getCpf() == null || aluno.getCpf().length() != 11) {
            log.error("Falha no cadastro: CPF {} é inválido!", aluno.getCpf());
            throw new IllegalArgumentException("CPF Inválido! Deve ter 11 dígitos.");
        }

        repository.save(aluno);
        log.info("Sucesso! Aluno {} cadastrado no banco de dados.", aluno.getNome());
    }

    public List<Aluno> listarAlunos() {
        log.info("Buscando lista de todos os alunos no banco de dados.");
        return repository.findAll();
    }

    public void limparBanco() {
        log.warn("ATENÇÃO: Comando para deletar todos os alunos foi acionado!");
        repository.deleteAll();
    }
}