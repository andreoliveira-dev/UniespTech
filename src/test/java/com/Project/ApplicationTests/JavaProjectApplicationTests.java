package com.Project.ApplicationTests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.projeto.uniespTech.main.JavaProjectApplication;

import static org.junit.jupiter.api.Assertions.*;


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