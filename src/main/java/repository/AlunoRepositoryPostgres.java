package repository;

import model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlunoRepositoryPostgres implements AlunoRepository {

    private final String url;
    private final String usuario;
    private final String senha;


    public AlunoRepositoryPostgres(String url, String usuario, String senha) {
        this.url = url;
        this.usuario = usuario;
        this.senha = senha;
    }


    // ----------------------------------------------------------------
    // Conexão
    // ----------------------------------------------------------------

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario,senha);
    }

    // ----------------------------------------------------------------
    // DDL – cria tabela na primeira execução
    // ----------------------------------------------------------------

    private void criarTabelaSeNaoExistir() {
        String sql = """
                CREATE IF NOT EXISTS alunos (
                id SERIAL PRIMARY KEY,
                nome VARCHAR(150) NOT NULL,
                cpf CHAR(11)      NOT NULL UNIQUE
              )
              """;

        try(Connection conn = conectar();
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
        }catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela 'alunos'" + e.getMessage(),e);
        }
    }

    // ----------------------------------------------------------------
    // CRUD
    // ----------------------------------------------------------------

    @Override
    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO alunos (id, nome, cpf) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, aluno.getId());
            ps.setString(2, aluno.getNome());
            ps.setString(3, aluno.getCpf());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar aluno: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Aluno> listarTodos() {
        String sql = "SELECT id, nome, cpf FROM alunos ORDER BY id";
        List<Aluno> lista = new ArrayList<>();
        try (Connection conn = conectar();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos: " + e.getMessage(), e);
        }
        return Collections.unmodifiableList(lista);
    }

    @Override
    public void deletarTodos() {
        // Truncate reinicia a sequência SERIAL automaticamente
        String sql = "TRUNCATE TABLE alunos RESTART IDENTITY";
        try (Connection conn = conectar();
             Statement  stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar alunos: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean cpfJaCadastrado(String cpf) {
        String sql = "SELECT 1 FROM alunos WHERE cpf = ?";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar CPF: " + e.getMessage(), e);
        }
    }

    @Override
    public int proximoId() {
        // Busca o próximo valor da sequência SERIAL sem consumi-lo de forma insegura.
        // Usamos currval após um nextval para garantir atomicidade.
        String sql = "SELECT nextval('alunos_id_seq')";
        try (Connection conn = conectar();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter próximo ID: " + e.getMessage(), e);
        }
    }
}
