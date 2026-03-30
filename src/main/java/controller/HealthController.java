package controller;

import static spark.Spark.*;
import java.sql.Connection;
import config.DatabaseConnection;

public class HealthController {

    public static void start() {

        port(4567);

        get("/health", (req, res) -> {
            try (Connection conn = DatabaseConnection.getConnection()) {
                return "OK - App e Banco funcionando";
            } catch (Exception e) {
                res.status(500);
                return "ERRO - Banco indisponível";
            }
        });
    }
}