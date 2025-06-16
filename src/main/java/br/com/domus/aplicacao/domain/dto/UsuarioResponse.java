package br.com.domus.aplicacao.domain.dto;

import java.time.LocalDate;

public record UsuarioResponse(String id, String nome, String email, LocalDate dataCadastro, boolean ativo,
		String telefone, boolean isAdmin) {
}
