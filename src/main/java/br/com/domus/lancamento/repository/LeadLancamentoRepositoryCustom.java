package br.com.domus.lancamento.repository;

import java.util.List;

import br.com.domus.lancamento.domain.LeadLancamentoEntity;
import br.com.domus.lancamento.service.QueryBuscaLancamentos;

public interface LeadLancamentoRepositoryCustom {
	List<LeadLancamentoEntity> buscarPorFiltros(QueryBuscaLancamentos query);
}
