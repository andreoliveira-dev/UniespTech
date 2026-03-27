import Controller.AlunoController;
import Controller.HealthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class SistemaUniesp {

    private static final Logger logger = LoggerFactory.getLogger(SistemaUniesp.class);

    public static void main(String[] args) {
        logger.info("🎓 Sistema Uniesp Tech - Iniciando aplicação");
        logger.info("📦 Versão: 1.0.0");

        try {
            // Inicia o servidor HTTP para health check na porta 8080
            HealthController.startHealthServer(8080);
            logger.info("🌐 Servidor HTTP iniciado na porta 8080");

        } catch (IOException e) {
            logger.error("❌ Falha ao iniciar servidor HTTP", e);
        }

        // Inicia o sistema principal (console)
        logger.info("💻 Iniciando interface de console");
        AlunoController controller = new AlunoController();
        controller.iniciar();

        // Quando o console fechar, para o servidor health check
        HealthController.stopHealthServer();
        logger.info("🛑 Sistema Uniesp Tech - Encerrando aplicação");
    }
}