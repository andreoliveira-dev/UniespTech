package health;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;

public class HealthCheckServer {

    private static final int    PORTA   = 8080;
    private static final String DB_URL  = System.getenv("DB_URL");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASS = System.getenv("DB_PASS");

    public static void iniciar() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORTA), 0);
        server.createContext("/health", new HealthHandler());
        server.start();
        System.out.println("Health Check rodando em http://localhost:" + PORTA + "/health");
    }

    static class HealthHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String status   = verificarSaude();
            int    httpCode = status.contains("DOWN") ? 503 : 200;

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(httpCode, status.getBytes().length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(status.getBytes());
            }
        }

        private String verificarSaude() {
            String dbStatus;

            if (DB_URL == null || DB_USER == null || DB_PASS == null) {
                dbStatus = "DOWN - Variáveis de ambiente não configuradas (DB_URL, DB_USER, DB_PASS)";
            } else {
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                    dbStatus = conn.isValid(2) ? "UP" : "DOWN";
                } catch (Exception e) {
                    dbStatus = "DOWN - " + e.getMessage();
                }
            }

            String appStatus = "UP";
            String timestamp = LocalDateTime.now().toString();

            return String.format("""
                {
                  "timestamp": "%s",
                  "app": "%s",
                  "database": "%s",
                  "status": "%s"
                }
                """,
                    timestamp,
                    appStatus,
                    dbStatus,
                    dbStatus.equals("UP") ? "UP" : "DOWN"
            );
        }
    }
}