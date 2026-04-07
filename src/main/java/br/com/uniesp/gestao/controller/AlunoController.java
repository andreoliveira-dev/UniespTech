package br.com.uniesp.gestao.controller;

import br.com.uniesp.gestao.model.Aluno;
import br.com.uniesp.gestao.service.AlunoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public String salvar(@RequestBody Aluno aluno) {
        try {
            service.cadastrarAluno(aluno);
            return "Aluno cadastrado com sucesso!";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }

    @GetMapping
    public List<Aluno> listar() {
        return service.listarAlunos();
    }

    @DeleteMapping
    public String deletar() {
        service.limparBanco();
        return "Todos os dados foram apagados!";
    }
}