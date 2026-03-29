package com.example.UniespTechhh.service;

import com.example.UniespTechhh.model.Usuario;
import com.example.UniespTechhh.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public Usuario salvar(Usuario user) {
        // Nivel de acessinhooo
        if (user.getPerfil() == null) throw new RuntimeException("Escolha se é Aluno ou Professor!");

        // Ado a ado, minha perna desgramado!" Cada um no seu quadrado" kskks simples
        if (user.getPerfil().toString().equals("ALUNO")) user.setDisciplina(null);
        else user.setMatricula(null);

        return repo.save(user);
    }
    public Usuario atualizar(Long id, Usuario user) {
        // Se o 'user' já vier com um ID que existe no banco,
        // o Spring faz o Update automaticamente.
        return repo.save(user);
    }

    public List<Usuario> listarTodos() {
        return repo.findAll();
    }

    // AQUI: Deletar apenas uma pessoa (O Sniper)
    public void deletarPorId(Long id) {
        repo.deleteById(id); // O Spring já faz a busca e deleta pra você em 1 linha!
    }
}