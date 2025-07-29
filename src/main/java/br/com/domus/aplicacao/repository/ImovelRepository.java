package br.com.domus.aplicacao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.domus.aplicacao.domain.ImovelEntity;

@Repository
public interface ImovelRepository extends MongoRepository<ImovelEntity, String>, ImovelRepositoryCustom {

}
