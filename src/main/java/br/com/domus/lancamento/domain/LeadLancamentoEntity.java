package br.com.domus.lancamento.domain;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.NonNull;

import br.com.domus.lancamento.domain.dto.LeadLancamentoDTO;
import br.com.domus.lancamento.domain.subdomain.DetalhesClienteLead;
import lombok.Data;

/**
 * Classe responsavel por representar a collection de dados que armazena as
 * Leads de interessados nos projetos lancamentos de empreendimento
 * 
 * @author lucas
 */
@Data
@Document(collection = "lead_lancamento")
public class LeadLancamentoEntity implements Serializable {
	private static final long serialVersionUID = 504459419719124288L;

	@Id
	private String id;

	@NonNull
	@Field(name = "nome_lacamento")
	private String nomeLancamento;

	@NonNull
	@Field(name = "nome_cliente")
	private String nomeCliente;

	@NonNull
	@Field(name = "telefone_cliente")
	private String telefoneCliente;

	@Field(name = "detalhes_cliente")
	private DetalhesClienteLead detalhesCliente;

	@Field(name = "usuario_opcionista_id")
	private String usuarioOpcionistaId;

	@Field(name = "is_lancamento_concluido")
	private boolean isLancamentoConcluido;
	
	@Field(name = "data_cadastro")
	private LocalDate dataCadastro;

	public LeadLancamentoEntity() {
	}

	public LeadLancamentoEntity(String nomeLancamento, String nomeCliente, boolean isLancamentoConcluido,
			String telefoneCliente) {
		super();
		this.nomeLancamento = nomeLancamento;
		this.nomeCliente = nomeCliente;
		this.isLancamentoConcluido = isLancamentoConcluido;
		this.telefoneCliente = telefoneCliente;
	}

	public LeadLancamentoEntity(LeadLancamentoDTO leadLancamentoDTO) {
		super();
		this.nomeLancamento = leadLancamentoDTO.nomeLancamento();
		this.nomeCliente = leadLancamentoDTO.nomeCliente();
		this.telefoneCliente = leadLancamentoDTO.telefoneCliente();
		this.dataCadastro = LocalDate.now();
	}

}
