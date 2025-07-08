package br.com.domus.lancamento.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import br.com.domus.lancamento.domain.LeadLancamentoEntity;
import br.com.domus.lancamento.service.QueryBuscaLancamentos;

@Repository
public class LeadLancamentoRepositoryCustomImpl implements LeadLancamentoRepositoryCustom {

	private MongoTemplate mongoTemplate;

	@Autowired
	public LeadLancamentoRepositoryCustomImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<LeadLancamentoEntity> buscarPorFiltros(QueryBuscaLancamentos query) {
		Query mongoQuery = new Query();
		Criteria criteria = new Criteria();

		if (query == null) {
			return mongoTemplate.findAll(LeadLancamentoEntity.class);
		}

		if (StringUtils.isNotBlank(query.getCorretorId())) {
			criteria.and("usuario_opcionista_id").in(query.getCorretorId());
		} else if (query.getIsSemCorretor() != null && query.getIsSemCorretor()) {
			criteria.and("usuario_opcionista_id").in(null, "");
		}

		if (StringUtils.isNotBlank(query.getNomeLancamento())) {
			criteria.and("nome_lacamento").in(query.getNomeLancamento(), "i");
		}

		if (StringUtils.isNotBlank(query.getNomeCliente())) {
			criteria.and("nome_cliente").regex(query.getNomeCliente(), "i");
		}
		mongoQuery.addCriteria(criteria);
		return mongoTemplate.find(mongoQuery, LeadLancamentoEntity.class);
	}

}
