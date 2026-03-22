package com.Projeto.UniespTech.main.model;

import com.Projeto.UniespTech.main.dto.AlunoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Aluno")
public class Aluno {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String Nome;

        @Column(nullable = false, unique = true)
        private String CPF;
}
