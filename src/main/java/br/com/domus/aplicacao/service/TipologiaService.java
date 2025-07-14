package br.com.domus.aplicacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domus.aplicacao.domain.TipologiaEntity;
import br.com.domus.aplicacao.repository.TipologiaRepository;

@Service
public class TipologiaService {

	@Autowired
	private TipologiaRepository tipologiaRepository;

	public List<TipologiaEntity> findAll() {
		return tipologiaRepository.findAll();
	}

	public TipologiaEntity findById(String tipologiaId) {
		return tipologiaRepository.findById(tipologiaId).orElseGet(null);
	}

	public void createTipologia(String nome) {
		tipologiaRepository.save(new TipologiaEntity(nome));
	}
}
