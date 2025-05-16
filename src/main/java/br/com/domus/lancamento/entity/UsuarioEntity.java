package br.com.domus.lancamento.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Value
@Document(collection = "usuario")
public class UsuarioEntity implements Serializable {
	private static final long serialVersionUID = -487711796456884420L;

	@Id
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private boolean isAtivo;
	private LocalDate dataCadastro;
	private LocalDate dataUltimaAtuaizacao;
}
