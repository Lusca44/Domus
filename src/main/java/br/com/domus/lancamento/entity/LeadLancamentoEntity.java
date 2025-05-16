package br.com.domus.lancamento.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Value
@Document(collection = "lead_lancamento")
public class LeadLancamentoEntity implements Serializable {
	private static final long serialVersionUID = 504459419719124288L;

	@Id
	private Long id;

	// private ProjetoLancamentoEntity projetoLancamento;

	private Long projetoLancamentoId;

	private String nomeCliente;

	// TODO Validar se vale a pena criar um obj pra isso. Talvez facilite e evite
	// erros existentens nas Leads tradicionais
	private String ddd;
	private String telefoneCliente;

//	private InformacoesContatoEntity informacoesContato;
	private Long informacoesContatoId;

	// TODO colocar objeto do usuario responsavel pela lead (funcionario,
	// corretor...
	// etc pensar em algo que se encaixe mais
	private String opcionista;
}
