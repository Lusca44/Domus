package br.com.domus.lancamento.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import br.com.domus.lancamento.domain.dto.CadastroLancamentoDTO;
import lombok.Data;

@Data
@Document(value = "lancamento")
public class LancamentoEntity implements Serializable {
	private static final long serialVersionUID = -2924723282881358702L;

	@Id
	private String id;

	@Field(name = "nome_lancamento")
	private String nomeLancamento;

	@Field(name = "url_foto_background")
	private String urlFotoBackGround;

	@Field(name = "url_fotos")
	private List<String> urlsFotos;

	@Field(name = "slogan")
	private String slogan;

	@Field(name = "regiao_id")
	private String regiaoId;

	@Field(name = "endereco")
	private String endereco;

	@Field(name = "sobre_lancamento")
	private SobreLancamento sobreLancamento;

	@Field(name = "diferenciais_lancamento")
	private List<String> diferenciaisLancamento;

	@Field(name = "proximidades_localizacao")
	private List<String> proximidadesDaLocalizacao;

	@Field(name = "localizacao_maps_source")
	private String localizacaoMapsSource;
	
	// CARD INFO
	@Field(name = "card_lancamento_info")
	private CardLancamentoInfo cardLancamentoInfo;
	

	public LancamentoEntity() {
	}
	
	public LancamentoEntity(CadastroLancamentoDTO cadastroLancamentoDTO) {
		super();
		this.nomeLancamento = cadastroLancamentoDTO.nomeLancamento();
		this.urlFotoBackGround = cadastroLancamentoDTO.urlFotoBackGround();
		this.urlsFotos = cadastroLancamentoDTO.urlsFotos();
		this.slogan = cadastroLancamentoDTO.slogan();
		this.regiaoId = cadastroLancamentoDTO.regiaoId();
		this.cardLancamentoInfo = new CardLancamentoInfo(cadastroLancamentoDTO.cardLancamentoInfo());
		this.endereco = cadastroLancamentoDTO.endereco();
		this.sobreLancamento = new SobreLancamento(cadastroLancamentoDTO.sobreLancamento());
		this.diferenciaisLancamento = cadastroLancamentoDTO.diferenciaisLancamento();
		this.proximidadesDaLocalizacao = cadastroLancamentoDTO.proximidadesDaLocalizacao();
		this.localizacaoMapsSource = cadastroLancamentoDTO.localizacaoMapsSource();
	}

}
