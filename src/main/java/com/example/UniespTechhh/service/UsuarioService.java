package com.example.UniespTechhh.service;

import com.example.UniespTechhh.model.Usuario;
import com.example.UniespTechhh.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    // Esse cara aqui e o fofoqueiro que vai contar tudo que acontece pro console ai eu vejo e que comece o bbb
    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository repo;

    // Esse aqui n e a unimed mas ve se o banco ta vivo obg helt healt
    public boolean bancoTaVivo() {
        try {
            repo.count(); // Tenta contar quantos tem so pra ver se o banco responde
            return true;
        } catch (Exception e) {
            logger.error("O BANCO BRECOUUU. O carrossel parou porque o H2 capotou ajuda YODA");
            return false;
        }
    }

    public Usuario salvar(Usuario user) {
        logger.info("Tentando salvar o meliante {}", user.getNome());

        // Nivel de acessinhooo
        if (user.getPerfil() == null) {
            logger.error("DEU RUIMMMM Esqueceram de falar se é Aluno ou Professor!");
            throw new RuntimeException("Escolha se e Aluno ou Professor");
        }

        // Se o CPF não tiver 11 números, o sistema nem olha na cara
        if (user.getCpf() == null || user.getCpf().length() != 11) {
            logger.error("B.O. NO CPF {} tá errado, precisa de 11 numeros! Ajeita ai que eu n sou a marcia sensitiva pra advinhar", user.getCpf());
            throw new RuntimeException("O CPF deve ter exatamente 11 numeros!");
        }

        if (user.getPerfil().toString().equals("ALUNO")) {
            user.setDisciplina(null);
        } else {
            user.setMatricula(null);
        }

        logger.info("Tudo vry nice. Salvando {} no H2...", user.getNome());
        return repo.save(user);
    }

    public Usuario atualizar(Long id, Usuario user) {
        logger.info("Mudando o vizu do ID {}", id);
        // Garante que a gente ta mexendo na pessoa certa pq neh? atualizar todo mundo pra o nome baroes da pisadinha n pode
        user.setId(id);
        return repo.save(user);
    }

    public List<Usuario> listarTodos() {
        logger.info("Chamando todos os thunder cats");
        return repo.findAll();
    }

    // Aqui Deleta apenas uma pessoa O Sniper 007 ou sla ja mexi dms nisso
    public void deletarPorId(Long id) {
        logger.warn("MIRA CONFIRMADA! O sniper vai apagar o ID {}", id);
        repo.deleteById(id);
        logger.info("ID {} foi pra vala com sucesso", id);
    }

}