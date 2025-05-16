package br.com.domus.lancamento.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Value
@Document(collection = "projeto_lancamento")
public class ProjetoLancamentoEntity implements Serializable {
	private static final long serialVersionUID = 6560354399982266594L;

	@Id
	private Long id;
	private String nomeProjeto;
	private String endereco;
	private String nomeConstrutora;

//	private LandingPageLancamentoEntity dadosLandingPage;
	private Long LandingPageLancamentoId;
}
