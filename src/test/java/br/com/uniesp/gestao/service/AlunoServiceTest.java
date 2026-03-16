package br.com.uniesp.gestao.service;

import br.com.uniesp.gestao.model.Aluno;
import br.com.uniesp.gestao.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlunoServiceTest {

    private AlunoRepository repository;
    private AlunoService service;

    @BeforeEach
    void setUp() {
        repository = new AlunoRepository();
        service = new AlunoService(repository);
    }

    @Test
    void deveCadastrarAlunoComSucesso() {
        Aluno aluno = new Aluno("João", "12345678901");
        Aluno salvo = service.cadastrar(aluno);

        assertNotNull(salvo.getId());
        assertEquals("João", salvo.getNome());
        assertEquals(1, service.listarAlunos().size());
    }

    @Test
    void deveDeletarTodosOsAlunos() {
        service.cadastrar(new Aluno("Maria", "10987654321"));
        service.deletarTudo();

        assertEquals(0, service.listarAlunos().size());
    }
}