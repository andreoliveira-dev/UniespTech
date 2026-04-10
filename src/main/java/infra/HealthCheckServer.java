package infra;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;

public class HealthCheckServer {

    private static final Logger log = LoggerFactory.getLogger(HealthCheckServer.class);

    public static void iniciar() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            server.createContext("/health", exchange -> {
                String resposta;
                int status;

                try (Connection conn = DatabaseConnection.obterConexao()) {
                    conn.isValid(2);
                    resposta = "{\n  \"status\": \"UP\",\n  \"database\": \"UP\"\n}";
                    status = 200;
                    log.info("Health check OK - banco conectado");
                } catch (Exception e) {
                    resposta = "{\n  \"status\": \"DOWN\",\n  \"database\": \"DOWN\"\n}";
                    status = 503;
                    log.error("Health check FALHOU - banco indisponivel: {}", e.getMessage());
                }

                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(status, resposta.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(resposta.getBytes());
                }
            });

            server.start();
            log.info("Health Check disponivel em http://localhost:8080/health");

        } catch (Exception e) {
            log.error("Erro ao iniciar servidor de Health Check: {}", e.getMessage());
        }
    }
}