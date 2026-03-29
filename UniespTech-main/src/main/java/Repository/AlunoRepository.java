package Repository;

import Model.Aluno;
import Config.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {

    private static final Logger logger = LoggerFactory.getLogger(AlunoRepository.class);

    public AlunoRepository() {
        logger.info("🚀 Inicializando AlunoRepository");
        criarTabela();
    }

    private void criarTabela() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS aluno (" +
                    "id IDENTITY PRIMARY KEY, " +
                    "nome VARCHAR(100), " +
                    "cpf VARCHAR(11) UNIQUE)");

            logger.info("✅ Tabela 'aluno' verificada/criada com sucesso");

        } catch (Exception e) {
            logger.error("❌ Erro ao criar tabela 'aluno'", e);
            e.printStackTrace();
        }
    }

    public void cadastrar(Aluno aluno) {
        logger.debug("💾 Salvando aluno no banco - Nome: {}, CPF: {}", aluno.getNome(), aluno.getCpf());

        String sql = "INSERT INTO aluno (nome, cpf) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            int rowsAffected = stmt.executeUpdate();

            logger.info("✅ Aluno persistido - Nome: {}, CPF: {}, Linhas afetadas: {}",
                    aluno.getNome(), aluno.getCpf(), rowsAffected);

        } catch (SQLException e) {
            logger.error("❌ Erro ao cadastrar aluno - Nome: {}, CPF: {}",
                    aluno.getNome(), aluno.getCpf(), e);
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public List<Aluno> listar() {
        logger.debug("🔍 Executando consulta para listar alunos");
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT nome, cpf FROM aluno";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getString("nome"),
                        rs.getString("cpf")
                ));
            }

            logger.info("📊 Consulta retornou {} alunos", alunos.size());

        } catch (Exception e) {
            logger.error("❌ Erro ao listar alunos", e);
            e.printStackTrace();
        }

        return alunos;
    }

    public void deletartodos() {
        logger.warn("⚠️ Executando deleção em massa de alunos");
        String sql = "DELETE FROM aluno";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            int rowsDeleted = stmt.executeUpdate(sql);
            logger.info("🗑️ {} alunos foram deletados", rowsDeleted);

        } catch (Exception e) {
            logger.error("❌ Erro ao deletar todos os alunos", e);
            e.printStackTrace();
        }
    }
}