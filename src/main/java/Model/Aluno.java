package Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;



@Table(name = "alunos")
@Entity(name = "Aluno")

public class Aluno {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @Size(min = 11, max = 11)
    private String cpf;

    public Aluno(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }


}
