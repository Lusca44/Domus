package br.com.domus.lancamento.service;

import lombok.Value;

@Value
public class QueryBuscaLancamentos {
	private Boolean isSemCorretor; //
	private String corretorId;
	private String nomeLancamento; //
	private String nomeCliente; //

}
