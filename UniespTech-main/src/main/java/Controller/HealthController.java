package Controller;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import Config.DatabaseConnection;
import java.io.OutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Executors;

public class HealthController {

    private static HttpServer server;

    public static void startHealthServer(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/health", new HealthHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("✅ Health check server rodando na porta " + port);
        System.out.println("📍 Endpoint: http://localhost:" + port + "/health");
    }

    public static void stopHealthServer() {
        if (server != null) {
            server.stop(0);
            System.out.println("🛑 Health check server parado");
        }
    }

    static class HealthHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response;
            int statusCode;

            // Configurar CORS para permitir acesso de qualquer origem
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().set("Content-Type", "application/json");

            try {
                // Testa conexão com o banco
                Connection conn = DatabaseConnection.getConnection();
                boolean isDatabaseUp = conn != null && !conn.isClosed();

                if (isDatabaseUp && conn.isValid(2)) {
                    response = String.format(
                            "{\"status\":\"UP\",\"database\":\"H2\",\"timestamp\":\"%s\",\"version\":\"1.0.0\"}",
                            java.time.LocalDateTime.now()
                    );
                    statusCode = 200;
                } else {
                    response = String.format(
                            "{\"status\":\"DOWN\",\"database\":\"H2\",\"error\":\"Connection failed\",\"timestamp\":\"%s\"}",
                            java.time.LocalDateTime.now()
                    );
                    statusCode = 503;
                }

                if (conn != null) conn.close();

            } catch (SQLException e) {
                response = String.format(
                        "{\"status\":\"DOWN\",\"database\":\"H2\",\"error\":\"%s\",\"timestamp\":\"%s\"}",
                        e.getMessage().replace("\"", "\\\""),
                        java.time.LocalDateTime.now()
                );
                statusCode = 503;
                e.printStackTrace();
            } catch (Exception e) {
                response = String.format(
                        "{\"status\":\"DOWN\",\"error\":\"%s\",\"timestamp\":\"%s\"}",
                        e.getMessage().replace("\"", "\\\""),
                        java.time.LocalDateTime.now()
                );
                statusCode = 500;
            }

            exchange.sendResponseHeaders(statusCode, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}