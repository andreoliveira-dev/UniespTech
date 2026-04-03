package repository;

import model.Aluno;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlunoRepositoryMemoria implements AlunoRepository {

    private final List<Aluno> alunos = new ArrayList<>();
    private int contadorId = 0;

    @Override
    public void salvar(Aluno aluno) {
        alunos.add(aluno);
    }

    @Override
    public List<Aluno> listarTodos() {
        return Collections.unmodifiableList(alunos);
    }

    @Override
    public void deletarTodos() {
        alunos.clear();
        contadorId = 0;
    }


    @Override
    public boolean cpfJaCadastrado(String cpf) {
        return alunos.stream().anyMatch(a -> a.getCpf().equals(cpf));
    }

    @Override
    public int proximoId() {
        return contadorId++;
    }

}
