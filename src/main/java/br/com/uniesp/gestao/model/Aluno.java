package br.com.uniesp.gestao.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;

public class Aluno {

    private String id = UUID.randomUUID().toString();

    @NotBlank(message = "ERRO: Nome não pode ser vazio!")
    private String nome;

    @NotBlank(message = "ERRO: CPF não pode ser vazio!")
    @Pattern(regexp = "\\d{11}", message = "ERRO: CPF Inválido! Deve ter exatamente 11 dígitos numéricos.")
    private String cpf;

    // Construtor vazio exigido pelo Spring
    public Aluno() {}

    public Aluno(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    // Getters e Setters
    public String getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}