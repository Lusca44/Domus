package br.com.domus.lancamento.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import br.com.domus.lancamento.domain.dto.CardLancamentoInfoDTO;
import lombok.Data;

@Data
public class CardLancamentoInfo {

	@Field(name = "valor")
	private String valor;

	@Field(name = "quartos_disponiveis")
	private List<String> quartosDisponiveis;

	@Field(name = "is_card_destaque")
	private boolean isCardDestaque;

	@Field(name = "areas_disponiveis")
	private List<String> areasDisponiveis;

	@Field(name = "finalidade_id")
	private String finalidadeId;

	@Field(name = "tipologia_id")
	private List<String> tipologiaId;

	@Field(name = "status_obra")
	private String statusObra;

	@Field(name = "url_imagem_card")
	private String urlImagemCard;

	public CardLancamentoInfo() {
		super();
	}

	public CardLancamentoInfo(CardLancamentoInfoDTO cadastroDTO) {
		this.valor = cadastroDTO.valor();
		this.quartosDisponiveis = cadastroDTO.quartosDisponiveis();
		this.isCardDestaque = cadastroDTO.isCardDestaque();
		this.areasDisponiveis = cadastroDTO.areasDisponiveis();
		this.finalidadeId = cadastroDTO.finalidadeId();
		this.tipologiaId = cadastroDTO.tipologiaId();
		this.statusObra = cadastroDTO.statusObra();
		this.urlImagemCard = cadastroDTO.urlImagemCard();
	}

}
