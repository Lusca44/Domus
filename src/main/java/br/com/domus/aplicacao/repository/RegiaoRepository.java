package br.com.domus.aplicacao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.domus.aplicacao.domain.RegiaoEntity;

@Repository
public interface RegiaoRepository extends MongoRepository<RegiaoEntity, String>{

}
