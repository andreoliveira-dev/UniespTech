package SistemaUniesp.Felipe.service;

import SistemaUniesp.Felipe.model.Aluno;
import SistemaUniesp.Felipe.repository.AlunoRepository;
import SistemaUniesp.Felipe.util.CpfValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }
    public Aluno salvar(Aluno aluno) {
        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            throw new RuntimeException("Nome é Obrigatorio");
        }
        if (!CpfValidator.isCpfValido(aluno.getCpf())){
            throw new RuntimeException("CPF invalido");
        }
        return repository.save(aluno);
    }

    public List<Aluno> listar(){
        return repository.findAll();
    }
}
