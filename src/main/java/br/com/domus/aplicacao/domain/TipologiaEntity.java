package br.com.domus.aplicacao.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value = "tipologia")
public class TipologiaEntity {

	@Id
	private String id;

	private String nome;

	public TipologiaEntity(String nome) {
		super();
		this.nome = nome;
	}
}
