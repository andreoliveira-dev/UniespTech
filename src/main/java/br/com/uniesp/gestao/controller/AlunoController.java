package br.com.uniesp.gestao.controller;

import br.com.uniesp.gestao.model.Aluno;
import br.com.uniesp.gestao.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrar(@Valid @RequestBody Aluno aluno) {
        Aluno novoAluno = service.cadastrar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listar() {
        return ResponseEntity.ok(service.listarAlunos());
    }

    @DeleteMapping("/apagar-tudo")
    public ResponseEntity<String> deletarTudo() {
        service.deletarTudo();
        return ResponseEntity.ok("Todos os dados foram apagados!");
    }
}