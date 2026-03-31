package controller;

import model.Aluno;
import service.AlunoService;
import java.util.List;

public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    public String cadastrarAluno(String nome, String cpf) {
        return service.cadastrar(nome, cpf);
    }

    public List<Aluno> listarAlunos() {
        return service.listarTodos();
    }

    public String deletarTodos(boolean confirmado) {
        if (!confirmado) {
            return "Operação cancelada.";
        }
        service.deletarTodos();
        return "Todos os dados foram apagados!";
    }
}
