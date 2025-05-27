package br.com.domus.lancamento.domain.subdomain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

import br.com.domus.lancamento.domain.LeadLancamentoEntity;
import br.com.domus.lancamento.domain.subdomain.dto.DetalhesClienteLeadDTO;
import lombok.Data;

/**
 * Classe representante da SubCollection da entidade de Leads de lancamentos
 * 
 * @see LeadLancamentoEntity
 * 
 * @author lucas
 */
@Data
public class DetalhesClienteLead implements Serializable {
	private static final long serialVersionUID = 9119754010445404973L;

	@Field(name = "is_ligacao_realizada")
	private boolean isLigacaoRealizada;

	@Field(name = "is_ligacao_atendida")
	private boolean isLigacaoAtendida;

	@Field(name = "is_numero_whatsapp")
	private boolean isNumeroWhatsapp;

	@Field(name = "is_mensagem_enviada")
	private boolean isMensagemEnviada;

	@Field(name = "is_mensagem_respondida")
	private boolean isMensagemRespondida;

	@Field(name = "is_declarou_interesse")
	private boolean isDeclarouInteresse;

	@Field(name = "is_comprou")
	private boolean isComprou;

	@Field(name = "is_solicitou_exclusao")
	private boolean isSolicitouExclusao;

	@Field(name = "anotacoes_cliente")
	private String anotacoesCliente;

	public DetalhesClienteLead() {
	}
	
	public DetalhesClienteLead(DetalhesClienteLeadDTO detalhesClienteLeadDTO) {
		super();
		this.isLigacaoRealizada = detalhesClienteLeadDTO.isLigacaoRealizada();
		this.isLigacaoAtendida = detalhesClienteLeadDTO.isLigacaoAtendida();
		this.isNumeroWhatsapp = detalhesClienteLeadDTO.isNumeroWhatsapp();
		this.isMensagemEnviada = detalhesClienteLeadDTO.isMensagemEnviada();
		this.isMensagemRespondida = detalhesClienteLeadDTO.isMensagemRespondida();
		this.isDeclarouInteresse = detalhesClienteLeadDTO.isDeclarouInteresse();
		this.isComprou = detalhesClienteLeadDTO.isComprou();
		this.isSolicitouExclusao = detalhesClienteLeadDTO.isSolicitouExclusao();
		this.anotacoesCliente = detalhesClienteLeadDTO.anotacoesCliente();
	}
}
