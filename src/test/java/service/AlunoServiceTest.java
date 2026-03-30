package service;

import model.Aluno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import repository.AlunoRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlunoServiceTest {

    //repositorio fake para n depender do banco.
    private AlunoRepository repositoryFake;
    private AlunoService service;

    @BeforeEach
    void setUp() {
        repositoryFake = new AlunoRepositoryFake();
        service = new AlunoService(repositoryFake);
    }

    // =========================================================
    // TESTES DE CADASTRO — NOME
    // =========================================================

    @Nested
    @DisplayName("Validações do Nome")
    class ValidacoesNome {

        @Test
        @DisplayName("Deve retornar erro quando nome for vazio")
        void deveRetornarErrorQuandoNomeVazio() {
            String resultado = service.cadastrar("", "12345678901");
            assertEquals("ERRO: Nome não pode ser vazio!", resultado);
        }

        @Test
        @DisplayName("Deve retornar erro quando nome for nulo")
        void deveRetornarErroQuandoNomeNulo() {
            String resultado = service.cadastrar(null, "12345678901");
            assertEquals("ERRO: Nome não pode ser vazio!", resultado);
        }

        @Test
        @DisplayName("Deve retornar erro quando nome tiver números")
        void deveRetornarErroQuandoNomeTiverNumeros() {
            String resultado = service.cadastrar("Jo4o Silva", "12345678901");
            assertEquals("ERRO: Nome não pode conter números ou caracteres especiais!", resultado);

        }

    }

    // =========================================================
    // TESTES DE CADASTRO — CPF
    // =========================================================

    @Nested
    @DisplayName("Validações do CPF")
    class ValidacoesCpf {

        @Test
        @DisplayName("Deve retornar erro quando CPF for vazio")
        void deveRetornarErroQuandoCpfVazio() {
            String resultado = service.cadastrar("João Silva", "");
            assertEquals("ERRO: CPF não pode ser vazio!", resultado);
        }

        @Test
        @DisplayName("Deve retornar erro quando CPF for nulo")
        void deveRetornarErroQuandoCpfNulo() {
            String resultado = service.cadastrar("João Silva", null);
            assertEquals("ERRO: CPF não pode ser vazio!", resultado);
        }

        @Test
        @DisplayName("Deve retornar erro quando CPF tiver letras")
        void deveRetornarErroQuandoCpfTiverLetras() {
            String resultado = service.cadastrar("João Silva", "1234567890A");
            assertEquals("ERRO: CPF deve conter apenas números!", resultado);
        }

        @Test
        @DisplayName("Deve retornar erro quando CPF tiver menos de 11 dígitos")
        void deveRetornarErroQuandoCpfCurto() {
            String resultado = service.cadastrar("João Silva", "1234567890");
            assertEquals("ERRO: CPF deve ter exatamente 11 dígitos!", resultado);
        }

        @Test
        @DisplayName("Deve retornar erro quando CPF tiver mais de 11 dígitos")
        void deveRetornarErroQuandoCpfLongo() {
            String resultado = service.cadastrar("João Silva", "123456789012");
            assertEquals("ERRO: CPF deve ter exatamente 11 dígitos!", resultado);
        }

    }

    // =========================================================
    // TESTES DE CADASTRO — SUCESSO
    // =========================================================
    @Nested
    @DisplayName("Cadastro com sucesso")
    class CadastroSucesso {

        @Test
        @DisplayName("Deve cadastrar aluno com dados válidos")
        void deveCadastrarAlunoComDadosValidos() {
            String resultado = service.cadastrar("João Silva", "12345678901");
            assertTrue(resultado.startsWith("Aluno cadastrado com sucesso!"));
        }

        @Test
        @DisplayName("Deve cadastrar aluno e aparecer na listagem")
        void deveCadastrarEListar() {
            service.cadastrar("João Silva", "12345678901");
            List<Aluno> alunos = service.listarTodos();
            assertEquals(1, alunos.size());
            assertEquals("João Silva", alunos.get(0).getNome());
        }

    }


    // =========================================================
    // TESTES DE LISTAGEM E DELEÇÃO
    // =========================================================


    @Nested
    @DisplayName("Listagem e Deleção")
    class ListagemDelecao {

        @Test
        @DisplayName("Deve retornar lista vazia quando não há alunos")
        void deveRetornarListaVaziaInicialmente() {
            assertTrue(service.listarTodos().isEmpty());
        }

        @Test
        @DisplayName("Deve deletar todos os alunos")
        void deveDeletarTodos() {
            service.cadastrar("João Silva", "12345678901");
            service.cadastrar("Maria Souza", "98765432100");
            service.deletarTodos();
            assertTrue(service.listarTodos().isEmpty());
        }

        @Test
        @DisplayName("Deve permitir recadastrar CPF após deletar tudo")
        void devePermitirRecadastrarAposDelecao() {
            service.cadastrar("João Silva", "12345678901");
            service.deletarTodos();
            String resultado = service.cadastrar("João Silva", "12345678901");
            assertTrue(resultado.startsWith("Aluno cadastrado com sucesso!"));
        }
    }

    // =========================================================
    // REPOSITÓRIO FAKE (substitui o real nos testes)
    // =========================================================

    static class AlunoRepositoryFake implements AlunoRepository {

        private final List<Aluno> alunos = new ArrayList<>();
        private int contadorId = 0;


        @Override
        public void salvar(Aluno aluno) {
            alunos.add(aluno);
        }

        @Override
        public List<Aluno> listarTodos() {
            return alunos;
        }

        @Override
        public void deletarTodos() {
            alunos.clear();
            contadorId = 0;
        }

        @Override
        public int proximoId() {
            return contadorId++;
        }

        @Override
        public boolean cpfJaCadastrado(String cpf) {
            return alunos.stream().anyMatch(a -> a.getCpf().equals(cpf));
        }
    }


}
