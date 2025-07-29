package br.com.domus.lancamento.domain.dto;

import java.util.List;

public record CardLancamentoInfoDTO(String valor, //
		List<String> quartosDisponiveis, //
		boolean isCardDestaque, //
		List<String> areasDisponiveis, //
		String finalidadeId, //
		List<String> tipologiaId, //
		String statusObra,
		String urlImagemCard) {

}
