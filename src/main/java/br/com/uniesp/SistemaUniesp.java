package br.com.uniesp;

import java.sql.*;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SistemaUniesp {
    private static final Logger logger = LoggerFactory.getLogger(SistemaUniesp.class);

    // Configurações do Banco via Variáveis de Ambiente (Padrão para Render/Docker)
    private static final String URL = System.getenv("DATABASE_URL") != null ? 
                                      System.getenv("DATABASE_URL") : 
                                      "jdbc:postgresql://db:5432/uniesp_db";
    private static final String USER = System.getenv("DATABASE_USER") != null ? 
                                       System.getenv("DATABASE_USER") : 
                                       "sloan";
    private static final String PASS = System.getenv("DATABASE_PASSWORD") != null ? 
                                       System.getenv("DATABASE_PASSWORD") : 
                                       "holding123";

    public static void main(String[] args) {
        SpringApplication.run(SistemaUniesp.class, args);
        logger.info("Sistema Uniesp iniciado com sucesso!");
    }

    @GetMapping("/")
    public String home() {
        return "Sistema Uniesp Tech Online! Use /health para verificar o banco.";
    }

    @GetMapping("/health")
    public String health() {
        if (checkSystemHealth()) {
            return "{\"status\": \"UP\", \"database\": \"CONNECTED\"}";
        } else {
            return "{\"status\": \"UP\", \"database\": \"DISCONNECTED\"}";
        }
    }

    public static boolean checkSystemHealth() {
        logger.info("Executando Health Check...");
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            return conn.isValid(2);
        } catch (SQLException e) {
            logger.error("Falha na conexão com o banco: {}", e.getMessage());
            return false;
        }
    }
}
