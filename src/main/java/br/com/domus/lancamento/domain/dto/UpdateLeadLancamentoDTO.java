package br.com.domus.lancamento.domain.dto;

import br.com.domus.lancamento.domain.LeadLancamentoEntity;

/**
 * Classe record responsavel por armazenar os dados para atualizacao entidade
 * {@link LeadLancamentoEntity} no padrao DATA TRANSFER OBJECT
 * 
 * @author lucas
 */
public record UpdateLeadLancamentoDTO( //
		String nomeCliente, //
		String nomeLancamento, //
		String emailCliente, //
		String telefoneCliente, //
		String usuarioOpcionista) {
}
