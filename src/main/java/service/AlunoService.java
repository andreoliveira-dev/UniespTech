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
        log.info("Tentativa de cadastro - nome: {}, cpf: {}", nome, cpf);

        if (nome == null || nome.trim().isEmpty()) {
            log.warn("Cadastro rejeitado - nome vazio");
            return "ERRO: Nome não pode ser vazio!";
        }
        if (nome.trim().length() < 3) {
            log.warn("Cadastro rejeitado - nome muito curto: {}", nome);
            return "ERRO: Nome deve ter pelo menos 3 caracteres!";
        }
        if (!nome.trim().matches("[\\p{L}\\s]+")) {
            log.warn("Cadastro rejeitado - nome invalido: {}", nome);
            return "ERRO: Nome não pode conter números ou caracteres especiais!";
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            log.warn("Cadastro rejeitado - CPF vazio");
            return "ERRO: CPF não pode ser vazio!";
        }
        if (!cpf.matches("\\d+")) {
            log.warn("Cadastro rejeitado - CPF com letras: {}", cpf);
            return "ERRO: CPF deve conter apenas números!";
        }
        if (cpf.length() != 11) {
            log.warn("Cadastro rejeitado - CPF tamanho invalido: {}", cpf);
            return "ERRO: CPF deve ter exatamente 11 dígitos!";
        }
        if (cpfTodosDigitosIguais(cpf)) {
            log.warn("Cadastro rejeitado - CPF digitos iguais: {}", cpf);
            return "ERRO: CPF inválido! (ex: 11111111111 não é permitido)";
        }
        if (repository.cpfJaCadastrado(cpf)) {
            log.warn("Cadastro rejeitado - CPF ja cadastrado: {}", cpf);
            return "ERRO: CPF já cadastrado no sistema!";
        }

        int id = repository.proximoId();
        Aluno aluno = new Aluno(id, nome.trim(), cpf);
        repository.salvar(aluno);
        log.info("Aluno cadastrado - id: {}, nome: {}", id, nome);
        return "Aluno cadastrado com sucesso! (ID: " + id + ")";
    }

    private boolean cpfTodosDigitosIguais(String cpf) {
        return cpf.chars().distinct().count() == 1;
    }

    public List<Aluno> listarTodos() {
        log.info("Listando todos os alunos");
        return repository.listarTodos();
    }

    public void deletarTodos() {
        repository.deletarTodos();
        log.warn("TODOS os alunos foram deletados!");
    }
}