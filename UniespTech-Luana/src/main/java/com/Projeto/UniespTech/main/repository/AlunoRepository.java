package com.Projeto.UniespTech.main.repository;


import com.Projeto.UniespTech.main.model.Aluno;
import com.Projeto.UniespTech.main.dto.AlunoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    boolean existsByCpf(String cpf);
}