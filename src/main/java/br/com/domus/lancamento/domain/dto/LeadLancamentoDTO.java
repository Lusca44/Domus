package br.com.domus.lancamento.domain.dto;

import br.com.domus.lancamento.domain.LeadLancamentoEntity;

/**
 * Classe record responsavel por representar a entidade
 * {@link LeadLancamentoEntity} no padrao DATA TRANSFER OBJECT
 * 
 * @author lucas
 */
public record LeadLancamentoDTO(//
		String nomeLancamento, //
		String nomeCliente, //
		String telefoneCliente,
		String emailCliente) {

}
