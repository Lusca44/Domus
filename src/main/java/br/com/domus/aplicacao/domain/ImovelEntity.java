package br.com.domus.aplicacao.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(value = "imovel")
public class ImovelEntity {

	@Id
	private String id;

	@Field(name = "titulo")
	private String titulo;

	@Field(name = "finalidade_id")
	private List<String> finalidadeId;

	@Field(name = "tipologia_id")
	private List<String> tipologiaId;

	@Field(name = "regiao_id")
	private String regiaoId;

	@Field(name = "endereco")
	private String endereco;

	@Field(name = "quantidade_quartos")
	private String quantidadeQuartos;

	@Field(name = "quantidade_banheiros")
	private String quantidadeBanheiros;

	@Field(name = "quantidade_vagas")
	private String quantidadeVagas;

	@Field(name = "quantidade_suites")
	private String quantidadeSuites;

	@Field(name = "area_quadrada")
	private String areaQuadrada;

	@Field(name = "descricao_imovel")
	private String descricaoImovel;

	@Field(name = "valor")
	private String valor;

	@Field(name = "valor_condominio")
	private String valorCondominio;

	@Field(name = "valor_iptu")
	private String valorIptu;

	@Field(name = "url_localizacao_maps")
	private String urlLocalizacaoMaps;
}
