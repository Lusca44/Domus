package br.com.domus.lancamento.entity;

import java.io.Serializable;

import lombok.Value;

@Value
public class CorretorEntity implements Serializable {
	private static final long serialVersionUID = 7384682816722810261L;

	private Long id;
	private UsuarioEntity usuarioCorretor;
}
