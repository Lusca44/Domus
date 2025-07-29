package br.com.domus.aplicacao.domain.dto;

import java.util.List;

public record CadastroImovelDTO( //
		String titulo, //
		String urlFotoCard, //
		List<String> urlsFotos, //
		List<String> finalidadeId, //
		List<String> tipologiaId, //
		String regiaoId, //
		String endereco, //
		String quantidadeQuartos, //
		String quantidadeBanheiros, //
		String quantidadeVagas, //
		String quantidadeSuites, //
		String areaQuadrada, //
		String descricaoImovel, //
		String valor, //
		String valorCondominio, //
		String valorIptu, //
		String urlLocalizacaoMaps //
) {

}
