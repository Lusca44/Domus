package br.com.domus.lancamento.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class CaracteristicasLancamento implements Serializable{
	private static final long serialVersionUID = -4903107968798471621L;

	@Field(name = "titulo")
	private String titulo;

	@Field(name = "valor")
	private String valor;
	
	@Field(name = "icone")
	private String icone;
}
