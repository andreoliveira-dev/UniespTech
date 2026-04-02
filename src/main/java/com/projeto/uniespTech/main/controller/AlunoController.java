package
com.projeto.uniespTech.main.controller;

import com.projeto.uniespTech.main.dto.AlunoDTO;
import com.projeto.uniespTech.main.model.Aluno;
import com.projeto.uniespTech.main.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping("/alunos")
    public ResponseEntity<List<Aluno>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @PostMapping("/alunos")
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody @Valid AlunoDTO alunoDTO) {
        Aluno aluno = alunoService.cadastrarAluno(alunoDTO);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/alunos")
    public ResponseEntity<Void> deletarAlunos() {
        alunoService.deletarAlunos();
        return ResponseEntity.noContent().build();
    }
}