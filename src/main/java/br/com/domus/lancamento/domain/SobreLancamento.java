package br.com.domus.lancamento.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class SobreLancamento {

	@Field(name = "titulo")
	private String titulo;
	
	@Field(name = "texto")
	private String texto;

	@Field(name = "cards_sobre_lancamento")
	private List<CardsSobreLancamento> cardsSobreLancamento;
}
