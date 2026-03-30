package repository;

import model.Aluno;
import config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {

    public AlunoRepository() {
        criarTabela();
    }

    private void criarTabela() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS aluno (" +
                    "id IDENTITY PRIMARY KEY, " +
                    "nome VARCHAR(100), " +
                    "cpf VARCHAR(11) UNIQUE)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cadastrar(Aluno aluno) {
        System.out.println("SALVANDO NO BANCO");  //teste para ver se o h2 está realmente funcionando

        String sql = "INSERT INTO aluno (nome, cpf) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public List<Aluno> listar() {
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alunos;
    }

    public void deletartodos() {
        String sql = "DELETE FROM aluno";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}