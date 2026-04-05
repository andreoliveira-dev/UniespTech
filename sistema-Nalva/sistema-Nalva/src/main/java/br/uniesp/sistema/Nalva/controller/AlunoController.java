package br.uniesp.sistema.Nalva.controller;

import br.uniesp.sistema.Nalva.model.Aluno;
import br.uniesp.sistema.Nalva.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/alunos")
@RestController
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service){
        this.service = service;
    }
    @PostMapping
    public Aluno cadastrar(@RequestBody Aluno aluno){
        return service.salvar(aluno);
    }
    @GetMapping
    public List<Aluno> listar(){
        return service.listar();
    }



}
