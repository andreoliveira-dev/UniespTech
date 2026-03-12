package Controller;

import Model.Aluno;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("alunos")
public class AlunoController {


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid String nome, String cpf){
        if (cpf == null || cpf.length() != 11) {
            return ResponseEntity.badRequest().body("CPF deve ter 11 dígitos");
        }
        var aluno = new Aluno(nome, cpf);

        return ResponseEntity.status(201).body(aluno);
    }
}
