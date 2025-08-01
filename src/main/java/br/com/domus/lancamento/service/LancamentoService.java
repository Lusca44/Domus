package br.com.domus.lancamento.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domus.imagem.service.ImageService;
import br.com.domus.lancamento.domain.LancamentoEntity;
import br.com.domus.lancamento.domain.dto.CadastroLancamentoDTO;
import br.com.domus.lancamento.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	ImageService imageService;

	public List<LancamentoEntity> findAll() {
		return lancamentoRepository.findAll();
	}

	public LancamentoEntity findById(String lancamentoId) {
		return lancamentoRepository.findById(lancamentoId).orElseGet(null);
	}

	public void createLancamento(CadastroLancamentoDTO cadastroLancamentoDTO) {
		LancamentoEntity entity = new LancamentoEntity(cadastroLancamentoDTO);
		lancamentoRepository.save(entity);
	}

	public void updateLancamento(String lancamentoId, CadastroLancamentoDTO updateDTO) {
		LancamentoEntity entity = findById(lancamentoId);
		
		entity.setNomeLancamento(updateDTO.nomeLancamento());
		entity.setUrlFotoBackGround(updateDTO.urlFotoBackGround());
		entity.setUrlsFotos(updateDTO.urlsFotos());
		entity.setSlogan(updateDTO.slogan());
		entity.setRegiaoId(updateDTO.regiaoId());
		
		entity.getCardLancamentoInfo().setAreasDisponiveis(updateDTO.cardLancamentoInfo().areasDisponiveis());
		entity.getCardLancamentoInfo().setCardDestaque(updateDTO.cardLancamentoInfo().isCardDestaque());
		entity.getCardLancamentoInfo().setFinalidadeId(updateDTO.cardLancamentoInfo().finalidadeId());
		entity.getCardLancamentoInfo().setQuartosDisponiveis(updateDTO.cardLancamentoInfo().quartosDisponiveis());
		entity.getCardLancamentoInfo().setStatusObra(updateDTO.cardLancamentoInfo().statusObra());
		entity.getCardLancamentoInfo().setTipologiaId(updateDTO.cardLancamentoInfo().tipologiaId());
		entity.getCardLancamentoInfo().setUrlImagemCard(updateDTO.cardLancamentoInfo().urlImagemCard());
		entity.getCardLancamentoInfo().setValor(updateDTO.cardLancamentoInfo().valor());
		
		entity.setEndereco(updateDTO.endereco());
		
		entity.setDiferenciaisLancamento(updateDTO.diferenciaisLancamento());
		entity.setProximidadesDaLocalizacao(updateDTO.proximidadesDaLocalizacao());
		entity.setLocalizacaoMapsSource(updateDTO.localizacaoMapsSource());
		
		lancamentoRepository.save(entity);
	}

	public void deleteLancamento(String lancamentoId) {
		LancamentoEntity lancamento = findById(lancamentoId);
		
		List<String> urlsIamges = new ArrayList<>();

		if (lancamento.getUrlFotoBackGround() != null) {
			urlsIamges.add(lancamento.getUrlFotoBackGround());
		}

		if (lancamento.getUrlsFotos() != null) {
			urlsIamges.addAll(lancamento.getUrlsFotos());
		}

		if (lancamento.getCardLancamentoInfo() != null && //
				lancamento.getCardLancamentoInfo().getUrlImagemCard() != null) {

			urlsIamges.add(lancamento.getCardLancamentoInfo().getUrlImagemCard());
		}
		lancamentoRepository.deleteById(lancamentoId);

		imageService.deleteImageFiles(urlsIamges);
	}

	public void deleteImagemLancamento(String lancamentoId, String urlImagem) {
		LancamentoEntity lancamento = findById(lancamentoId);

		if (lancamento.getUrlFotoBackGround() != null && lancamento.getUrlFotoBackGround().equals(urlImagem)) {
			lancamento.setUrlFotoBackGround(null);
		}

		if (lancamento.getUrlsFotos() != null && !lancamento.getUrlsFotos().isEmpty()) {
			lancamento.getUrlsFotos().removeIf(url -> url.equals(urlImagem));
		}

		if (lancamento.getCardLancamentoInfo() != null && lancamento.getCardLancamentoInfo().getUrlImagemCard() != null
				&& lancamento.getCardLancamentoInfo().getUrlImagemCard().equals(urlImagem)) {
			lancamento.getCardLancamentoInfo().setUrlImagemCard(null);
		}

		if (lancamento.getUrlFotoBackGround().equals(urlImagem)) {
			lancamento.setUrlFotoBackGround(null);
		}

		lancamentoRepository.save(lancamento);
	}
}
