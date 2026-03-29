package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfiguracaoBanco {

    // Caminho do arquivo dentro do classpath (src/main/resources)
    private static final String ARQUIVO = "/banco.properties";

    private final String url;
    private final String usuario;
    private final String senha;

    public ConfiguracaoBanco() {

        // Tenta ler as credenciais das variáveis de ambiente.
        // Útil em produção, Docker ou CI sem expor senha no código.
        String envUrl     = System.getenv("DB_URL");
        String envUsuario = System.getenv("DB_USUARIO");
        String envSenha   = System.getenv("DB_SENHA");

        // Carrega o banco.properties como fallback,
        // caso as variáveis de ambiente não estejam definidas.
        Properties props = new Properties();
        try (InputStream is = getClass().getResourceAsStream(ARQUIVO)) {
            if (is == null) {
                throw new RuntimeException(
                        "Arquivo '" + ARQUIVO + "' não encontrado no classpath. " +
                                "Verifique src/main/resources/banco.properties");
            }
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler configurações do banco: " + e.getMessage(), e);
        }

        // Prioridade: variável de ambiente > banco.properties
        this.url     = envUrl     != null ? envUrl     : props.getProperty("db.url");
        this.usuario = envUsuario != null ? envUsuario : props.getProperty("db.usuario");
        this.senha   = envSenha   != null ? envSenha   : props.getProperty("db.senha");
    }

    public String getUrl()     { return url;     }
    public String getUsuario() { return usuario; }
    public String getSenha()   { return senha;   }
}
