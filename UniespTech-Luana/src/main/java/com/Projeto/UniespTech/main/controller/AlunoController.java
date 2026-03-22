package com.Projeto.UniespTech.main.controller;

import com.Projeto.UniespTech.main.dto.AlunoDTO;
import com.Projeto.UniespTech.main.model.Aluno;
import com.Projeto.UniespTech.main.service.AlunoService;
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

    @GetMapping("/listAlu")
    public ResponseEntity<List<Aluno>> listadeAlunos(){
        List<Aluno> listaDosAlunos = alunoService.receberAlunosCadastro();

        return ResponseEntity.ok(listaDosAlunos);
    }

    @PostMapping ("/cadastrar")
    public ResponseEntity<String> cadastrarAluno(@RequestBody @Valid AlunoDTO alunoDTO){
        alunoService.cadastrarAlunos(alunoDTO);
        return ResponseEntity.ok("Aluno cadastrado com sucesso!");
    }

    @DeleteMapping("/deletar/todos")
    public ResponseEntity<String> deletarAlunos ()
    {
        alunoService.deletarAlunos();

        return ResponseEntity.ok("Todos os alunos deletados com sucesso!");
    }
}
