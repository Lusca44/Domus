package br.com.domus.aplicacao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.domus.aplicacao.domain.TipologiaEntity;

public interface TipologiaRepository extends MongoRepository<TipologiaEntity, String>{

}
