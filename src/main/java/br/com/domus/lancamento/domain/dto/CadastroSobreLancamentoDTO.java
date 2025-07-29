package br.com.domus.lancamento.domain.dto;

import java.util.List;

public record CadastroSobreLancamentoDTO( //
		String titulo, //
		String texto, //
		List<CadastroCardsSobreLancamentoDTO> cardsSobreLancamento //
) {
}
