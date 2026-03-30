package com.Projeto.UniespTech.main.service;

import com.Projeto.UniespTech.main.dto.AlunoDTO;
import com.Projeto.UniespTech.main.model.Aluno;
import com.Projeto.UniespTech.main.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public Aluno cadastrarAlunos(AlunoDTO alunoDTO) {

        if (alunoRepository.existsByCPF(alunoDTO.cpf())) {
            throw new IllegalArgumentException("Aluno já cadastrado!");
        }

        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.nome());
        aluno.setCpf(alunoDTO.cpf());

        return alunoRepository.save(aluno);
    }

    public List<Aluno> receberAlunosCadastro() {
        List<Aluno> listadeAlunos = alunoRepository.findAll();
        return listadeAlunos;
    }

    public void deletarAlunos() {
        alunoRepository.deleteAll();
    }
}
