package br.uniesp.sistema.Nalva.service;

import br.uniesp.sistema.Nalva.model.Aluno;
import br.uniesp.sistema.Nalva.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

// Importações corretas do JUnit 5
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @Test
    void deveSalvarAluno() {
        Aluno aluno = new Aluno("Nalva", "12345678901");
        when(alunoRepository.save(aluno)).thenReturn(aluno);

        Aluno resultado = alunoService.salvar(aluno);

        assertNotNull(resultado);
        // Cuidado com espaços extras: "Nalva " vs "Nalva"
        assertEquals("Nalva", resultado.getNome());
    }

    @Test
    void deveListarAlunos() {
        // Arrange
        List<Aluno> lista = List.of(
                new Aluno("Nalva", "12345678901"),
                new Aluno("Julia", "00987654321")
        );
        when(alunoRepository.findAll()).thenReturn(lista);

        // Act - CORREÇÃO AQUI: usando a instância alunoService
        List<Aluno> resultado = alunoService.listar();

        // Assert
        assertEquals(2, resultado.size()); // Verifica o tamanho da lista
        verify(alunoRepository).findAll();
    }
}