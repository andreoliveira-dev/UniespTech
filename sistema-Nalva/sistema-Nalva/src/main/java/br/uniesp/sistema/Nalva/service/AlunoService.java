package br.uniesp.sistema.Nalva.service;

import br.uniesp.sistema.Nalva.model.Aluno;
import br.uniesp.sistema.Nalva.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    // 1. Removido o 'static' e adicionado o 'final'
    private final AlunoRepository repository;

    // 2. Construtor padrão para Injeção de Dependência (sem static)
    public AlunoService(AlunoRepository repository){
        this.repository = repository;
    }

    public Aluno salvar(Aluno aluno){
        return repository.save(aluno);
    }


    public List<Aluno> listar() {
        return repository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public Aluno atualizar(Long id, Aluno alunoAtualizado) {
        Aluno aluno = buscarPorId(id);
        aluno.setNome(alunoAtualizado.getNome());
        aluno.setCpf(alunoAtualizado.getCpf());
        return repository.save(aluno);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}