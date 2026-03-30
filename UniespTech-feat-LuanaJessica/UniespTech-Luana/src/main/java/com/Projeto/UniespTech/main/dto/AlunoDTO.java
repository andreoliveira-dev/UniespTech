package com.Projeto.UniespTech.main.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AlunoDTO(@NotBlank(message = "Nome é obrigatório") String nome, @NotBlank(message = "Adicionar o CPF é obrigatório!")
@Pattern( regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11}", message = "CPF deve estar no formato XXX.XXX.XXX-XX ou conter 11 números") String cpf) {
}
