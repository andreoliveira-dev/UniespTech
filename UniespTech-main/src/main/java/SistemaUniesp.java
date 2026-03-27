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

        // Verifica se está rodando no Render ou ambiente cloud
        boolean isCloud = System.getenv("RENDER") != null || System.getenv("PORT") != null;

        if (!isCloud) {
            // Modo local: inicia o console
            logger.info("💻 Iniciando interface de console (modo local)");
            AlunoController controller = new AlunoController();
            controller.iniciar();

            // Quando o console fechar, para o servidor health check
            HealthController.stopHealthServer();
        } else {
            // Modo cloud: mantém apenas o servidor HTTP rodando
            logger.info("☁️ Modo cloud detectado - Mantendo apenas servidor HTTP");
            logger.info("✅ Sistema pronto! Acesse /health para verificar status");

            // Mantém a aplicação rodando
            while (true) {
                try {
                    Thread.sleep(60000); // Aguarda 1 minuto
                    logger.debug("🔄 Heartbeat - Sistema funcionando");
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        logger.info("🛑 Sistema Uniesp Tech - Encerrando aplicação");
    }
}