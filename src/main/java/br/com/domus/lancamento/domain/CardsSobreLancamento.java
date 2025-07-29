package br.com.domus.lancamento.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

import br.com.domus.lancamento.domain.dto.CadastroCardsSobreLancamentoDTO;
import lombok.Data;

@Data
public class CardsSobreLancamento implements Serializable {
	private static final long serialVersionUID = 1172893175222374315L;

	@Field(name = "icone")
	private String icone;

	@Field(name = "titulo")
	private String titulo;

	@Field(name = "texto")
	private String texto;

	public CardsSobreLancamento() {
	}
	
	public CardsSobreLancamento(CadastroCardsSobreLancamentoDTO cadastroDTO) {
		super();
		this.icone = cadastroDTO.icone();
		this.titulo = cadastroDTO.titulo();
		this.texto = cadastroDTO.texto();
	}

}
