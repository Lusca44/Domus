package br.com.domus.lancamento.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class CardsSobreLancamento {

	@Field(name = "icone")
	private String icone;

	@Field(name = "titulo")
	private String titulo;
	
	@Field(name = "texto")
	private String texto;
}
