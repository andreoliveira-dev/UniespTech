package br.com.uniesp.gestao.service;

import br.com.uniesp.gestao.model.Aluno;
import br.com.uniesp.gestao.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno cadastrar(Aluno aluno) {
        return repository.salvar(aluno);
    }

    public List<Aluno> listarAlunos() {
        return repository.listarTodos();
    }

    public void deletarTudo() {
        repository.deletarTudo();
    }
}