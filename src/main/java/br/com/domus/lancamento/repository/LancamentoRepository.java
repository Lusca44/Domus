package br.com.domus.lancamento.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.domus.lancamento.domain.LancamentoEntity;

@Repository
public interface LancamentoRepository extends MongoRepository<LancamentoEntity, String> {

}
