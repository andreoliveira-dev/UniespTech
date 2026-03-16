package com.example.java_project.Service;

import com.example.java_project.Dto.AlunoDto;
import com.example.java_project.Model.Aluno;
import com.example.java_project.Repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;

    public void CadstrarAluno(AlunoDto alunoDto)
    {
        if (repository.existsByCPF(alunoDto.cpf()))
            throw new RuntimeException("Esse aluno já foi cadastrado!");

        if (alunoDto.cpf().length() != 11)
            throw new RuntimeException("O CPF digitado é invalido");

        Aluno aluno = new Aluno();
        aluno.setNome(alunoDto.nome());
        aluno.setCPF(alunoDto.cpf());
        repository.save(aluno);
    }

    public List<Aluno> ReceberTodosAluno()
    {
        return repository.findAll();
    }

    public void DeletarTodosAlunos()
    {
        
    }
}
