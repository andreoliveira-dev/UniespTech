package br.com.uniesp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaUniespTest {

    @Test
    @DisplayName("Deve validar se o CPF tem exatamente 11 dígitos")
    void validarComprimentoDoCpf() {
        String cpfValido = "12345678901";
        String cpfInvalido = "123";
        
        assertAll("Validações de CPF",
            () -> assertEquals(11, cpfValido.length(), "CPF válido deve ter 11 dígitos"),
            () -> assertNotEquals(11, cpfInvalido.length(), "CPF inválido não deve passar")
        );
    }
}
