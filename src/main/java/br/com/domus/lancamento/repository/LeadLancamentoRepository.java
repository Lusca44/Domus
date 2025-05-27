package br.com.domus.lancamento.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.domus.lancamento.domain.LeadLancamentoEntity;

@Repository
public interface LeadLancamentoRepository extends MongoRepository<LeadLancamentoEntity, String> {

	List<LeadLancamentoEntity> findByNomeLancamento(String nomeLancamento);

	@Query("{'corretorOpcionistaId': { $regex: ?0, $options: 'i' } }")
	List<LeadLancamentoEntity> findAllByCorretorOpcionistaId(Long corretorOpcionistaId);
}
