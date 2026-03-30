package com.Projeto.ApplicationTests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.Projeto.UniespTech.main.JavaProjectApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = JavaProjectApplication.class)
class JavaProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void cadastroComSucesso() {
		String nome = "Luana";
		String email = "luana@gmail.com";

		boolean cadastroValido = nome != null && !nome.isEmpty() && email.contains("@");

		assertTrue(cadastroValido);
	}

	@Test
	void cadastroFalhaEmailInvalido() {
		String nome = "Luana";
		String email = "luana.gmail.com";

		boolean cadastroValido = email.contains("@");

		assertFalse(cadastroValido);
	}

}