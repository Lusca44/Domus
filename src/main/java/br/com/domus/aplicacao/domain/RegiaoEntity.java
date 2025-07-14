package br.com.domus.aplicacao.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(value = "regiao")
public class RegiaoEntity {

	@Id
	private String id;

	@Field(name = "nome_regiao")
	private String nomeRegiao;
	
	@Field(name = "is_destaque")
	private boolean isDestaque;

	public RegiaoEntity(String nomeRegiao) {
		super();
		this.nomeRegiao = nomeRegiao;
		this.isDestaque = false;
	}
	
	
	
}
