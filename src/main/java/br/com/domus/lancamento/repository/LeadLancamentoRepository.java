package br.com.domus.lancamento.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.domus.lancamento.domain.LeadLancamentoEntity;

@Repository
public interface LeadLancamentoRepository extends MongoRepository<LeadLancamentoEntity, String>, LeadLancamentoRepositoryCustom {

	List<LeadLancamentoEntity> findByNomeLancamento(String nomeLancamento);

	@Query("{'usuario_opcionista_id': { $regex: ?0, $options: 'i' } }")
	List<LeadLancamentoEntity> findAllByCorretorOpcionistaId(String corretorOpcionistaId);
}
