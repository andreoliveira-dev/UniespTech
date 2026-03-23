package SistemaUniesp.Felipe.controller;


import SistemaUniesp.Felipe.model.Aluno;
import SistemaUniesp.Felipe.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {this.service = service;}
    @PostMapping
    public Aluno Cadastrar(@RequestBody Aluno aluno) {return service.salvar(aluno);}

    @GetMapping
    public List<Aluno> categoria() {return service.listar();}
    @GetMapping("/teste")
    public String teste() {return "Olha o Passito!";}
}
