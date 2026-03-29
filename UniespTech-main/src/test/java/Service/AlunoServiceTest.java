package Service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlunoServiceTest {

    private AlunoService service;

    @BeforeEach
    void setUp() {
        service = new AlunoService();
        service.deletarTodos();
    }

    @Test
    void deveCadastrarAlunoComDadosValidos() {
        boolean resultado = service.cadastrarAluno("Lucas Accioly", "98765432100");
        assertTrue(resultado);
    }

    @Test
    void naoDeveCadastrarAlunoComNomeVazio() {
        boolean resultado = service.cadastrarAluno("", "98765432100");
        assertFalse(resultado);
    }

    @Test
    void naoDeveCadastrarAlunoComCpfInvalido() {
        boolean resultado = service.cadastrarAluno("Lucas Accioly", "12345");
        assertFalse(resultado);
    }

    @Test
    void deveListarAlunoCadastrado() {
        service.cadastrarAluno("Lucas Accioly", "11122233344");
        assertEquals(1, service.listarAlunos().size());
    }
}