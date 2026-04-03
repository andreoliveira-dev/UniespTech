package repository;

import model.Aluno;
import java.util.List;

public interface AlunoRepository {

    void salvar(Aluno aluno);

    List<Aluno> listarTodos();
    void deletarTodos();
    int proximoId();
    boolean cpfJaCadastrado(String cpf);

}
