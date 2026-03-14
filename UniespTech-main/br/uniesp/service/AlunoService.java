package br.uniesp.service;

import br.uniesp.model.Aluno;
import br.uniesp.repository.AlunoRepository;
import java.util.List;


public class AlunoService {

    private AlunoRepository repository = new AlunoRepository();

    public boolean cadastrarAluno(String nome, String cpf) {
        if (nome == null || nome.isBlank()) {
            System.out.println("Não pode ser vazio ");

            return false;
        }

        if (!cpf.matches("\\d{11}")) {
            System.out.println("CPF deve ter 11 números");
            return false;

        }

        Aluno aluno = new Aluno(nome, cpf);
        repository.cadastrar(aluno);

        return true;
    }

    public List<Aluno> listarAlunos() {
        return repository.listar();
    }

    public void deletarTodos() {
        repository.deletartodos();
    }
}
