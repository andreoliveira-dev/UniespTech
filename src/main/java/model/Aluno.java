package model;

public class Aluno {

    private int id;
    private String nome;
    private String cpf;

    public Aluno(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public String getCpf(){
        return cpf;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Nome: " + nome +
                " | CPF: " + cpf;
    }
}
