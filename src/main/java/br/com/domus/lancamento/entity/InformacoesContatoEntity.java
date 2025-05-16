package br.com.domus.lancamento.entity;

import java.io.Serializable;

import lombok.Value;

@Value
public class InformacoesContatoEntity implements Serializable {
	private static final long serialVersionUID = 9119754010445404973L;

	private Long id;
	private boolean isLigacaoRealizada;
	private boolean isLigacaoAtendida;

	private boolean isContatoWhatsapp;

	private boolean isMensagemEnviada;
	private boolean isMensagemRespondida;
	private boolean isDeclarouInteresse;
	private boolean isComprou;
	private boolean isSolicitouRemocao;

	private String anotacoes;
}
