package Controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class AlunoControllerTest {

    @Test
    void deveriaValidarCpfComOnzeDigitos() {
        AlunoController controller = new AlunoController();

        // 1. Chama o método e guarda a resposta
        ResponseEntity<?> resultado = controller.cadastrar("João", "1234567890");

        // 2. Verifica se o status retornado indica erro (ex: 400 Bad Request)
        // Se o seu código retorna 400 quando o CPF é curto, use:
        assertEquals(400, resultado.getStatusCodeValue(), "Deveria retornar erro 400 para CPF inválido");

        // OU, se você quiser apenas verificar que NÃO foi um sucesso (2xx):
        assertFalse(resultado.getStatusCode().is2xxSuccessful(), "O cadastro não deveria ter sucesso com CPF inválido");
    }
}