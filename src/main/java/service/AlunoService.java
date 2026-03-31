package service;

import model.Aluno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.AlunoRepository;

import java.util.List;

public class AlunoService {

    private static final Logger log = LoggerFactory.getLogger(AlunoService.class);

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public String cadastrar(String nome, String cpf) {

        log.info("Tentativa de cadastro — nome: '{}'", nome);

        // Validação do nome
        if (nome == null || nome.trim().isEmpty()) {
            log.warn("Cadastro rejeitado — nome vazio ou nulo");
            return "ERRO: Nome não pode ser vazio!";
        }
        if (nome.trim().length() < 3) {
            log.warn("Cadastro rejeitado — nome muito curto: '{}'", nome);
            return "ERRO: Nome deve ter pelo menos 3 caracteres!";
        }
        if (!nome.trim().matches("[a-zA-ZÀ-ÿ\\s]+")) {
            log.warn("Cadastro rejeitado — nome com caracteres inválidos: '{}'", nome);
            return "ERRO: Nome não pode conter números ou caracteres especiais!";
        }

        // Validação do CPF
        if (cpf == null || cpf.trim().isEmpty()) {
            log.warn("Cadastro rejeitado — CPF vazio ou nulo");
            return "ERRO: CPF não pode ser vazio!";
        }
        if (!cpf.matches("\\d+")) {
            log.warn("Cadastro rejeitado — CPF com caracteres não numéricos");
            return "ERRO: CPF deve conter apenas números!";
        }
        if (cpf.length() != 11) {
            log.warn("Cadastro rejeitado — CPF com tamanho inválido: {} dígitos", cpf.length());
            return "ERRO: CPF deve ter exatamente 11 dígitos!";
        }
        if (cpfTodosDigitosIguais(cpf)) {
            log.warn("Cadastro rejeitado — CPF com todos dígitos iguais");
            return "ERRO: CPF inválido! (ex: 11111111111 não é permitido)";
        }
        if (repository.cpfJaCadastrado(cpf)) {
            log.warn("Cadastro rejeitado — CPF já cadastrado no sistema");
            return "ERRO: CPF já cadastrado no sistema!";
        }

        int id = repository.proximoId();
        Aluno aluno = new Aluno(id, nome.trim(), cpf);
        repository.salvar(aluno);

        log.info("Aluno cadastrado com sucesso — ID: {}, nome: '{}'", id, nome.trim());
        return "Aluno cadastrado com sucesso! (ID: " + id + ")";
    }

    // CPFs como 00000000000, 11111111111 são inválidos
    private boolean cpfTodosDigitosIguais(String cpf) {
        return cpf.chars().distinct().count() == 1;
    }

    public List<Aluno> listarTodos() {
        List<Aluno> alunos = repository.listarTodos();
        log.info("Listagem realizada — {} aluno(s) encontrado(s)", alunos.size());
        return alunos;
    }

    public void deletarTodos() {
        repository.deletarTodos();
        log.warn("ATENÇÃO — Todos os alunos foram deletados do sistema!");
    }
}