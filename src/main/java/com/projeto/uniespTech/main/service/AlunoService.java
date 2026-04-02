package com.projeto.uniespTech.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projeto.uniespTech.main.dto.AlunoDTO;
import com.projeto.uniespTech.main.model.Aluno;
import com.projeto.uniespTech.main.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;

    private static final Logger logger = LoggerFactory.getLogger(AlunoService.class);

    public Aluno cadastrarAluno(AlunoDTO alunoDTO) {

        logger.info("Iniciando cadastro do aluno: {}", alunoDTO.nome());

        if (alunoRepository.existsByCpf(alunoDTO.cpf())) {
            logger.error("CPF já cadastrado: {}", alunoDTO.cpf());
            throw new IllegalArgumentException("Aluno já cadastrado!");
        }

        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.nome());
        aluno.setCpf(alunoDTO.cpf());

        logger.info("Aluno cadastrado com sucesso: {}", aluno.getNome());

        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> listadeAlunos = alunoRepository.findAll();
        return listadeAlunos;
    }

    public void deletarAlunos() {
        logger.warn("Deletando todos os alunos do sistema");
        alunoRepository.deleteAll();
    }
}
