package br.com.uniesp.gestao.repository;

import br.com.uniesp.gestao.model.Aluno;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlunoRepository {

    private final List<Aluno> alunos = new ArrayList<>();

    public Aluno salvar(Aluno aluno) {
        alunos.add(aluno);
        return aluno;
    }

    public List<Aluno> listarTodos() {
        // Retorna uma cópia para proteger a lista original
        return new ArrayList<>(alunos);
    }

    public void deletarTudo() {
        alunos.clear();
    }
}