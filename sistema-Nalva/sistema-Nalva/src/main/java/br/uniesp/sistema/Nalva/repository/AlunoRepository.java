package br.uniesp.sistema.Nalva.repository;

import br.uniesp.sistema.Nalva.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
