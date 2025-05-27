package br.com.domus.lancamento.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Value
@Document(collection = "corretor")
public class CorretorEntity implements Serializable {

	private static final long serialVersionUID = 7384682816722810261L;

	@Id
	private Long id;
	private Long usuarioId;
}
