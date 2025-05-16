package br.com.domus.lancamento.entity;

import java.io.Serializable;

import lombok.Value;

@Value
public class ProjetoLancamentoEntity implements Serializable {
	private static final long serialVersionUID = 6560354399982266594L;

	private Long id;
	private String nomeProjeto;
	private String endereco;
	private String nomeConstrutora;

	private LandingPageLancamentoEntity dadosLandingPage;
}
