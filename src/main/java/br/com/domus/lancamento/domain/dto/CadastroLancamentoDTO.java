package br.com.domus.lancamento.domain.dto;

import java.util.List;

public record CadastroLancamentoDTO( //
		String nomeLancamento, //
		String urlFotoBackGround,  //
		List<String> urlsFotos, //
		String slogan, //
		String regiaoId, //
		CardLancamentoInfoDTO cardLancamentoInfo, //
		String endereco, //
		CadastroSobreLancamentoDTO sobreLancamento, //
		List<String> diferenciaisLancamento, //
		List<String> proximidadesDaLocalizacao, //
		String localizacaoMapsSource //
) {
}
