package com.example.java_project.Repository;

import com.example.java_project.Dto.AlunoResponseDto;
import com.example.java_project.Model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    boolean existsByCPF(String alunoCPF);

}
