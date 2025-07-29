package br.com.domus.aplicacao.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import br.com.domus.aplicacao.domain.ImovelEntity;

@Repository
public class ImovelRepositoryCustomImpl implements ImovelRepositoryCustom {

	private MongoTemplate mongoTemplate;

	@Autowired
	public ImovelRepositoryCustomImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<ImovelEntity> buscarFinalidade(String finalidadeId) {
		Query mongoQuery = new Query();
		Criteria criteria = new Criteria();

		if (StringUtils.isBlank(finalidadeId)) {
			return mongoTemplate.findAll(ImovelEntity.class);
		}
		criteria.and("finalidade_id").in(finalidadeId);
		mongoQuery.addCriteria(criteria);
		return mongoTemplate.find(mongoQuery, ImovelEntity.class);
	}

}
