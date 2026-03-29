package com.example.UniespTechhh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data//Pesquisei
@Entity//Diz pro banco que isso e uma tabela/entidade
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Gera o ID sozinho pra n ter que ficar 0, 1, 2 e blablablablba facilitador
    private Long id;

    @NotBlank(message = "Nome vazio não rola!")//Validando pra n vir campo em branco pq ninguem e sem terra e sem registro, n quero dor de cabeça
    private String nome;

    @Size(min = 11, max = 11, message = "CPF tem que ter 11 números, é gorpe?!") //Regra do CPF e n ele n aceita cpf em branco a não ser que vc seja um sem regristro e sem terra ai "its not my problema"
    private String cpf;

    private String email;
    private String senha;

    // O enum salva o nivel como alunin ou professor, só acho que ta muito na cara e eu deveria por nivel de acesso 1 e 2 mas e oq tem(ALUNO/PROFESSOR)
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    private String matricula;
    private String disciplina;

    //Validação pra evitar bagunça no banco
    @PrePersist
    @PreUpdate
    public void validarDados() {

        if (perfil == Perfil.ALUNO) {
            if (matricula == null || matricula.isEmpty()) {
                throw new RuntimeException("Aluno precisa ter matrícula");
            }
            this.disciplina = null; // aluno não usa disciplina
        }

        if (perfil == Perfil.PROFESSOR) {
            if (disciplina == null || disciplina.isEmpty()) {
                throw new RuntimeException("Professor precisa ter disciplina");
            }
            this.matricula = null; // professor não usa matrícula
        }
    }
}