package com.Project.ApplicationTests.service;

import com.projeto.uniespTech.main.dto.AlunoDTO;
import com.projeto.uniespTech.main.model.Aluno;
import com.projeto.uniespTech.main.repository.AlunoRepository;
import com.projeto.uniespTech.main.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @Test
    void deveCadastrarAlunoComSucesso() {
        AlunoDTO dto = new AlunoDTO("Luana", "12345678901");

        when(alunoRepository.existsByCpf(dto.cpf())).thenReturn(false);
        when(alunoRepository.save(any(Aluno.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Aluno aluno = alunoService.cadastrarAluno(dto);

        assertNotNull(aluno);
        assertEquals("Luana", aluno.getNome());
        assertEquals("12345678901", aluno.getCpf());
    }

    @Test
    void deveFalharQuandoCpfJaExiste() {
        AlunoDTO dto = new AlunoDTO("Luana", "12345678901");

        when(alunoRepository.existsByCpf(dto.cpf())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            alunoService.cadastrarAluno(dto);
        });
    }

    @Test
    void deveFalharQuandoNomeVazio() {
        AlunoDTO dto = new AlunoDTO("", "12345678901");

        assertThrows(IllegalArgumentException.class, () -> {
            alunoService.cadastrarAluno(dto);
        });
    }

    @Test
    void deveFalharQuandoCpfInvalido() {
        AlunoDTO dto = new AlunoDTO("Luana", "123");

        assertThrows(IllegalArgumentException.class, () -> {
            alunoService.cadastrarAluno(dto);
        });
    }

    @Test
    void deveListarAlunos() {
        when(alunoRepository.findAll())
                .thenReturn(java.util.List.of(new Aluno()));

        var lista = alunoService.listarAlunos();

        assertNotNull(lista);
        assertEquals(1, lista.size());
    }

    @Test
    void deveDeletarTodosAlunos() {
        alunoService.deletarAlunos();

        verify(alunoRepository, times(1)).deleteAll();
    }
}