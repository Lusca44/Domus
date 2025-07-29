package br.com.domus.lancamento.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import br.com.domus.lancamento.domain.dto.CadastroSobreLancamentoDTO;
import lombok.Data;

@Data
public class SobreLancamento implements Serializable {
	private static final long serialVersionUID = -293683387293810900L;

	@Field(name = "titulo")
	private String titulo;

	@Field(name = "texto")
	private String texto;

	@Field(name = "cards_sobre_lancamento")
	private List<CardsSobreLancamento> cardsSobreLancamento;

	public SobreLancamento() {
	}
	
	public SobreLancamento(CadastroSobreLancamentoDTO cadastroDTO) {
		this.titulo = cadastroDTO.titulo();
		this.texto = cadastroDTO.texto();
		this.cardsSobreLancamento = new ArrayList<>();

		List<CardsSobreLancamento> cards = new ArrayList<>();

		cadastroDTO.cardsSobreLancamento().forEach(card -> {
			cards.add(new CardsSobreLancamento(card));
		});
		this.cardsSobreLancamento.addAll(cards);

	}

}
