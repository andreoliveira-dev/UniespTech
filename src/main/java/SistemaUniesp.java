import health.HealthCheckServer;
import config.ConfiguracaoBanco;
import controller.AlunoController;
import repository.AlunoRepositoryPostgres;
import service.AlunoService;

import java.util.Scanner;

public class SistemaUniesp {

    public static void main(String[] args) throws Exception {

        HealthCheckServer.iniciar();

        ConfiguracaoBanco config = new ConfiguracaoBanco();
        AlunoRepositoryPostgres repository = new AlunoRepositoryPostgres(
                config.getUrl(),
                config.getUsuario(),
                config.getSenha()
        );

        AlunoService    service    = new AlunoService(repository);
        AlunoController controller = new AlunoController(service);

        // Se rodar no servidor (sem terminal), fica só o HealthCheck
        if (System.console() == null) {
            System.out.println("Modo servidor — aguardando requisições em /health");
            Thread.currentThread().join();
            return;
        }

        // Se rodar localmente, exibe o menu normal
        Scanner leitor = new Scanner(System.in);
        while (true) {
            // ... seu menu original aqui
        }
    }
}