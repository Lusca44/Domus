package br.com.domus.aplicacao.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("finalidade")
public class FinalidadeEntity {

	@Id
	private String id;
	private String nome;

	public FinalidadeEntity() {
	}

	public FinalidadeEntity(String nome) {
		this.nome = nome;
	}

}
