package br.com.uniesp.gestao.service;

import br.com.uniesp.gestao.model.Aluno;
import br.com.uniesp.gestao.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlunoServiceTest {

    @Mock
    private AlunoRepository repository;

    @InjectMocks
    private AlunoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarAlunoComSucesso() {
        Aluno aluno = new Aluno(1L, "João", "12345678901");
        assertDoesNotThrow(() -> service.cadastrarAluno(aluno));
        verify(repository, times(1)).save(aluno); // Verifica se mandou salvar no banco
    }

    @Test
    void naoDeveCadastrarAlunoComCpfInvalido() {
        Aluno alunoInvalido = new Aluno(2L, "Maria", "123");
        assertThrows(IllegalArgumentException.class, () -> service.cadastrarAluno(alunoInvalido));
    }
}