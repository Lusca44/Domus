package br.com.domus.aplicacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domus.aplicacao.domain.RegiaoEntity;
import br.com.domus.aplicacao.repository.RegiaoRepository;

@Service
public class RegiaoService {

	@Autowired
	private RegiaoRepository regiaoRepository;

	public List<RegiaoEntity> findAll() {
		return regiaoRepository.findAll();
	}

	public RegiaoEntity findById(String id) {
		return regiaoRepository.findById(id).orElseGet(null);
	}

	public void createRegiao(String nomeRegiao) {
		RegiaoEntity entity = new RegiaoEntity(nomeRegiao);
		regiaoRepository.save(entity);
	}

	public void deleteRegiao(String regiaoId) {
		regiaoRepository.deleteById(regiaoId);
	}

	public void alterarStatusDestaqueRegiao(String regiaoId) {
		RegiaoEntity entity = this.findById(regiaoId);
		entity.setDestaque(!entity.isDestaque());
		regiaoRepository.save(entity);
	}

}
