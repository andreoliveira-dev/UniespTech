package SistemaUniesp.Felipe.repository;

import SistemaUniesp.Felipe.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
