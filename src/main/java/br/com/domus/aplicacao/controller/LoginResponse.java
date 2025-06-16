package br.com.domus.aplicacao.controller;

import br.com.domus.aplicacao.domain.dto.UsuarioResponse;
import lombok.Value;

@Value
public class LoginResponse {
	private String token;
	private UsuarioResponse usuario;
}
