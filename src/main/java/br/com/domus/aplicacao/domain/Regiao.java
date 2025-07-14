package br.com.domus.aplicacao.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "regiao")
public class Regiao {

	@Id
	private String id;

	@Field(name = "nome_regiao")
	private String nomeRegiao;
	
	@Field(name = "is_destaque")
	private boolean isDestaque;
}
