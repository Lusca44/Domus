package br.com.domus.lancamento.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Value
@Document(collection = "landing_page_lancamento")
public class LandingPageLancamentoEntity {

	@Id
	private Long id;

	// TODO será necessário ver a quantidade de textos
	// TODO também será necessário ver como será feita a questão das imagens e
	// videos
}
