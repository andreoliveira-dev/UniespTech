package com.example.java_project.Controller;

import com.example.java_project.Dto.AlunoDto;
import com.example.java_project.Dto.AlunoResponseDto;
import com.example.java_project.Model.Aluno;
import com.example.java_project.Service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoService alunoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarAluno(@RequestBody @Valid AlunoDto dto)
    {
        alunoService.CadstrarAluno(dto);
        return ResponseEntity.ok("O aluno foi cadastrado");
    }

    @GetMapping("/receber")
    public ResponseEntity<List<Aluno>> receberListaAlunos()
    {
        List<Aluno> listaAluno = alunoService.ReceberTodosAluno();

        return ResponseEntity.ok(listaAluno);
    }

    @DeleteMapping("/deletar/todos")
    public ResponseEntity<String> deletarTodosAlunos() {
        alunoService.DeletarTodosAlunos();
        return ResponseEntity.ok("Todos os alunos Foram deletados");
    }
}
