package br.com.domus.aplicacao.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.domus.aplicacao.domain.UsuarioEntity;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioEntity, String> {
	Optional<UsuarioEntity> findByEmail(String email);

	boolean existsByEmail(String email);

}
