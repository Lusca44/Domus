package br.com.domus.aplicacao.repository;

import java.util.List;

import br.com.domus.aplicacao.domain.ImovelEntity;

public interface ImovelRepositoryCustom {
	List<ImovelEntity> buscarFinalidade(String nomeFinalidade);
}
