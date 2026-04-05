package br.uniesp.sistema.Nalva.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;

    public Aluno(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String toSting(){
        return "Aluno: " + nome  + "Cpf: " + cpf;
    }

    public void setNome(String nome) {
    }

    public void setCpf(String cpf) {

    }
}
