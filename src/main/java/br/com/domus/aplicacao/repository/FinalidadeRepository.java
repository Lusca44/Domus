package br.com.domus.aplicacao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.domus.aplicacao.domain.FinalidadeEntity;

@Repository
public interface FinalidadeRepository extends MongoRepository<FinalidadeEntity, String> {

}
