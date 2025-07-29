package br.com.domus.aplicacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domus.aplicacao.domain.FinalidadeEntity;
import br.com.domus.aplicacao.repository.FinalidadeRepository;

@Service
public class FinalidadeService {

	@Autowired
	private FinalidadeRepository finalidadeRepository;

	public List<FinalidadeEntity> findAll() {
		return finalidadeRepository.findAll();
	}

	public FinalidadeEntity findById(String tipologiaId) {
		return finalidadeRepository.findById(tipologiaId).orElseGet(null);
	}

	public void createFinalidade(String nome) {
		finalidadeRepository.save(new FinalidadeEntity(nome));
	}

	public void updateFinalidade(String idFinalidade, String nome) {
		FinalidadeEntity entity = findById(idFinalidade);
		entity.setNome(nome);
		finalidadeRepository.save(entity);
	}
	
	public void deleteFinalidade(String idFinalidade) {
		finalidadeRepository.deleteById(idFinalidade);
	}
}
