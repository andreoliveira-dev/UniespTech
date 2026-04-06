import java.sql.*;
import java.util.Scanner;

public class SistemaUniesp {
    // Configurações do Banco (conectando via Docker Compose)
    private static final String URL = "jdbc:postgresql://db:5432/uniesp_db";
    private static final String USER = "sloan";
    private static final String PASS = "holding123";

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        
        System.out.println("======= SISTEMA ACADÊMICO UNIESP TECH (PRO) =======");
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("[DB] Conexão estabelecida com sucesso!");

            // Criar tabela automaticamente se não existir
            String createTable = "CREATE TABLE IF NOT EXISTS alunos (id SERIAL PRIMARY KEY, nome TEXT, cpf TEXT)";
            conn.createStatement().execute(createTable);

            while (true) {
                System.out.println("\n1 - Cadastrar Aluno");
                System.out.println("2 - Listar Alunos");
                System.out.println("3 - Sair");
                System.out.print("Escolha: ");
                
                int opcao = leitor.nextInt();
                leitor.nextLine(); 

                if (opcao == 1) {
                    System.out.print("Nome: ");
                    String nome = leitor.nextLine();
                    System.out.print("CPF: ");
                    String cpf = leitor.nextLine();

                    String sql = "INSERT INTO alunos (nome, cpf) VALUES (?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, nome);
                    pstmt.setString(2, cpf);
                    pstmt.executeUpdate();
                    System.out.println(">>> Aluno salvo permanentemente no PostgreSQL!");

                } else if (opcao == 2) {
                    System.out.println("\n--- LISTA DE ALUNOS (BANCO DE DADOS) ---");
                    ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM alunos");
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id") + " | Nome: " + rs.getString("nome") + " | CPF: " + rs.getString("cpf"));
                    }
                } else if (opcao == 3) {
                    System.out.println("Encerrando sistema...");
                    break;
                }
            }
        } catch (SQLException e) {
            System.err.println("ERRO DE CONEXÃO: " + e.getMessage());
            System.out.println("Dica: Certifique-se de rodar com 'docker-compose up'");
        }
    }
}
