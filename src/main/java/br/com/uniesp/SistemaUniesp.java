package br.com.uniesp;

import java.sql.*;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SistemaUniesp {
    // Logger Profissional (Semana 3 - Monitoramento)
    private static final Logger logger = LoggerFactory.getLogger(SistemaUniesp.class);

    // Configurações do Banco (Conectando via Docker Compose)
    private static final String URL = "jdbc:postgresql://db:5432/uniesp_db";
    private static final String USER = "sloan";
    private static final String PASS = "holding123";

    public static void main(String[] args) {
        logger.info("======= INICIANDO SISTEMA ACADÊMICO UNIESP TECH (PRO) =======");
        
        // Executa o Health Check antes de abrir o menu
        if (!checkSystemHealth()) {
            logger.warn("O sistema iniciou, mas o Banco de Dados parece estar offline.");
        }

    }

    // Método de Health Check (Semana 3 - Monitoramento)
    public static boolean checkSystemHealth() {
        logger.info("Executando Health Check (Verificação de Saúde)...");
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            if (conn.isValid(2)) {
                logger.info("[HEALTH: OK] Conexão com PostgreSQL estabelecida.");
                
                // Garante que a tabela existe
                String createTable = "CREATE TABLE IF NOT EXISTS alunos (id SERIAL PRIMARY KEY, nome TEXT NOT NULL)";
                conn.createStatement().execute(createTable);
                return true;
            }
        } catch (SQLException e) {
            logger.error("[HEALTH: CRITICAL] Falha ao conectar no banco de dados!");
        }
        return false;
    }

    private static void cadastrarAluno(Scanner leitor) {
        System.out.print("Nome do Aluno: ");
        String nome = leitor.nextLine();

        if (nome.trim().isEmpty()) {
            logger.warn("Tentativa de cadastro com nome vazio bloqueada.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO alunos (nome) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.executeUpdate();
            logger.info("Aluno '{}' cadastrado com sucesso no PostgreSQL.", nome);
        } catch (SQLException e) {
            logger.error("Erro ao cadastrar aluno: {}", e.getMessage());
        }
    }

    private static void listarAlunos() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM alunos";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            System.out.println("\n--- LISTA DE ALUNOS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Nome: " + rs.getString("nome"));
            }
            logger.info("Listagem de alunos realizada com sucesso.");
        } catch (SQLException e) {
            logger.error("Erro ao listar alunos: {}", e.getMessage());
        }
    }
}
