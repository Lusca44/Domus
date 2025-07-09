package br.com.domus.aplicacao.domain;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.domus.aplicacao.domain.dto.UsuarioCadastroDTO;
import lombok.Data;

@Data
@Document(collection = "usuario")
public class UsuarioEntity implements Serializable {
	private static final long serialVersionUID = -487711796456884420L;

	@Id
	private String id;

	@Field(name = "nome")
	private String nome;

	@Field(name = "email")
	private String email;

	@Field(name = "senha")
	private String senha;

	@Field(name = "data_cadastro")
	@JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataCadastro;

	@Field(name = "is_ativo")
	private boolean isAtivo;

	@Field(name = "data_desativacao")
	private LocalDate dataDesativacao;

	@Field(name = "telefone")
	private String telefone;

	@Field(name = "is_admin")
	private boolean isAdmin;

	public UsuarioEntity() {
		super();
	}

	public UsuarioEntity(UsuarioCadastroDTO usuarioDTO) {
		super();
		this.nome = usuarioDTO.nome();
		this.email = usuarioDTO.email();
		this.senha = usuarioDTO.senha();
		this.telefone = usuarioDTO.telefone();
		this.isAdmin = usuarioDTO.isAdmin().contains("S");
		this.isAtivo = true;
		this.dataCadastro = LocalDate.now();
	}
}
