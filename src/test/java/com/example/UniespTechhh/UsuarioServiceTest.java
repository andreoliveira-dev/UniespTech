package com.example.UniespTechhh;//para n se assustar veja o readme primeiro ksksk coloquei uma mensagem bem legal quando esse carrosel roda

import com.example.UniespTechhh.model.Perfil;
import com.example.UniespTechhh.model.Usuario;
import com.example.UniespTechhh.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // O Abençoado testa do sistema riga ou deriga?
@Transactional // Limpa o banco depois do teste pro senhor girafales não ficar morando lá pra sempre junto com smzin jackson
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService servico;

    @Test //Teste do cadastro da victoria screts  pq da victoria screts?? pq o cadastro vai ficar bonito se passar , e feio se num passar
    public void testarCadastroSucesso() {
        Usuario user = new Usuario();
        user.setNome("Smzin michaeljackson da silva");
        user.setCpf("12345678901");
        user.setEmail("smzin@uniesp.com");
        user.setSenha("123456");
        user.setPerfil(Perfil.ALUNO);
        user.setMatricula("MAT-2026"); // Aluno precisa ter matrícula se não o PrePersist chora e num passa muhehehe

        Usuario salvo = servico.salvar(user);
        assertNotNull(salvo.getId()); //Se o banco deu um ID "Escuta aí o barulho do foguete" fununciou
    }

    @Test //Agora nivel e perfil mas ta dando pra entender
    public void testarProtecaoDeNivel() {
        Usuario prof = new Usuario();
        prof.setNome("Prof. Girafales");
        prof.setCpf("98765432100");
        prof.setEmail("girafales@vila.com");
        prof.setSenha("123456");
        prof.setPerfil(Perfil.PROFESSOR);
        prof.setDisciplina("Café com a Florinda"); // Professor tem disciplina mas n tem matricula :D por isso da pra saber quem ministra ja aluno tem ambos
        prof.setMatricula("SOU_ALUNO_SQN"); //vamos ver se n breca

        Usuario salvo = servico.salvar(prof);
        assertNull(salvo.getMatricula()); //Teste dnv e bora ver se deu b.o
    }
}