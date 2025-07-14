package br.com.domus.lancamento.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(value = "lancamento")
public class Lancamento {

	@Id
	private String id;

	@Field(name = "nome_lancamento")
	private String nomeLancamento;

	@Field(name = "url_fotos")
	private List<String> urlsFotos;
	
	@Field(name = "slogan")
	private String slogan;

	@Field(name = "regiao_id")
	private String regiaoId;

	@Field(name = "endereco")
	private String endereco;

	@Field(name = "caracteristicas")
	private CaracteristicasLancamento caracteristicas;

	@Field(name = "sobre_lancamento")
	private SobreLancamento sobreLancamento;

	@Field(name = "diferenciais_lancamento")
	private List<String> diferenciaisLancamento;

	@Field(name = "proximidades_localizacao")
	private List<String> proximidadesDaLocalizacao;

	@Field(name = "localizacao_maps_source")
	private String localizacaoMapsSource;
}
