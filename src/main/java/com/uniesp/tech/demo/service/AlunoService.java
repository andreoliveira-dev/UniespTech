package com.uniesp.tech.demo.service;

import com.uniesp.tech.demo.model.Aluno;
import com.uniesp.tech.demo.repository.AlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private static final Logger logger = LoggerFactory.getLogger(AlunoService.class);

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public Aluno cadastrarAluno(Aluno aluno) {
        logger.info("Tentativa de cadastrar aluno com CPF: {}", aluno.getCpf());
        try {
            Aluno alunoSalvo = alunoRepository.save(aluno);
            logger.info("Aluno cadastrado com sucesso! ID gerado: {}", alunoSalvo.getId());
            return alunoSalvo;
        } catch (Exception e) {
            logger.error("ERRO CRÍTICO ao cadastrar aluno: {}. Motivo: {}", aluno.getCpf(), e.getMessage());
            throw e;
        }
    }

    public List<Aluno> listarAlunos() {
        logger.debug("Buscando lista completa de alunos");
        return alunoRepository.findAll();
    }

    @Transactional
    public void deletarAlunoPorId(Long id) {
        logger.warn("Tentativa de deletar aluno com ID: {}", id);
        if (!alunoRepository.existsById(id)) {
            logger.error("Falha ao deletar: Aluno com ID {} não existe", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }
        alunoRepository.deleteById(id);
        logger.info("Aluno ID {} deletado com sucesso", id);
    }


    @Transactional
    public void atualizarAlunoPorId(Long id, Aluno aluno) {
        logger.info("Iniciando atualização do aluno ID: {}", id);

        Aluno alunoParaEditar = alunoRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Falha na atualização: Aluno ID {} não encontrado", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
                });

        alunoParaEditar.setNome(aluno.getNome());
        alunoParaEditar.setCpf(aluno.getCpf());

        try {
            alunoRepository.save(alunoParaEditar);
            logger.info("Aluno ID {} atualizado com sucesso", id);
        } catch (Exception e) {
            logger.error("Erro ao salvar atualização do aluno ID {}: {}", id, e.getMessage());
            throw e;
        }
    }
}