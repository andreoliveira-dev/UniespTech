package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfiguracaoBanco {

    private static final String ARQUIVO = "/banco.properties";

    private final String url;
    private final String usuario;
    private final String senha;

    public ConfiguracaoBanco() {
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
        this.url     = props.getProperty("db.url");
        this.usuario = props.getProperty("db.usuario");
        this.senha   = props.getProperty("db.senha");
    }

    public String getUrl()     { return url;     }
    public String getUsuario() { return usuario; }
    public String getSenha()   { return senha;   }
}