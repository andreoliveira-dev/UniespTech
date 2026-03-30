package service;

import model.Aluno;
import repository.AlunoRepository;

import java.util.List;

public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public String cadastrar(String nome, String cpf) {
        // Validação do nome
        if (nome == null || nome.trim().isEmpty()) {
            return "ERRO: Nome não pode ser vazio!";
        }
        if (nome.trim().length() < 3) {
            return "ERRO: Nome deve ter pelo menos 3 caracteres!";
        }
        if (!nome.trim().matches("[a-zA-ZÀ-ÿ\\s]+")) {
            return "ERRO: Nome não pode conter números ou caracteres especiais!";
        }

        // Validação do CPF
        if (cpf == null || cpf.trim().isEmpty()) {
            return "ERRO: CPF não pode ser vazio!";
        }
        if (!cpf.matches("\\d+")) {
            return "ERRO: CPF deve conter apenas números!";
        }
        if (cpf.length() != 11) {
            return "ERRO: CPF deve ter exatamente 11 dígitos!";
        }
        if (cpfTodosDigitosIguais(cpf)) {
            return "ERRO: CPF inválido! (ex: 11111111111 não é permitido)";
        }
        if (repository.cpfJaCadastrado(cpf)) {
            return "ERRO: CPF já cadastrado no sistema!";
        }

        int id = repository.proximoId();
        Aluno aluno = new Aluno(id, nome.trim(), cpf);
        repository.salvar(aluno);
        return "Aluno cadastrado com sucesso! (ID: " + id + ")";
    }

    // CPFs como 00000000000, 11111111111 são inválidos
    private boolean cpfTodosDigitosIguais(String cpf) {
        return cpf.chars().distinct().count() == 1;
    }

    public List<Aluno> listarTodos() {
        return repository.listarTodos();
    }

    public void deletarTodos() {
        repository.deletarTodos();
    }
}
