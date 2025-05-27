package br.com.domus.lancamento.domain.subdomain.dto;

import br.com.domus.lancamento.domain.LeadLancamentoEntity;

/**
 * Classe record responsavel por representar os detalhes da
 * {@link LeadLancamentoEntity} no padrao DATA TRANSFER OBJECT
 * 
 * @author lucas
 */
public record DetalhesClienteLeadDTO( //
		boolean isLigacaoRealizada, //
		boolean isLigacaoAtendida, //
		boolean isNumeroWhatsapp, //
		boolean isMensagemEnviada, //
		boolean isMensagemRespondida, //
		boolean isDeclarouInteresse, //
		boolean isComprou, //
		boolean isSolicitouExclusao, //
		String anotacoesCliente //
) {

}
