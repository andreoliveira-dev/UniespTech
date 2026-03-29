package Controller;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class HealthController {

    private static HttpServer server;

    public static void startHealthServer(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0);

        // Endpoint /health
        server.createContext("/health", new HealthHandler());

        // Endpoint raiz / para teste
        server.createContext("/", new RootHandler());

        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("✅ Health check server rodando na porta " + port);
        System.out.println("📍 Endpoint: http://0.0.0.0:" + port + "/health");
        System.out.println("📍 Teste: http://0.0.0.0:" + port + "/");
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
            String response = "{\"status\":\"UP\",\"message\":\"Servidor funcionando!\"}";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "✅ Servidor Uniesp Tech está rodando! Acesse /health para verificar status.";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}