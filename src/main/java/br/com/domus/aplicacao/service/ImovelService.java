package br.com.domus.aplicacao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domus.aplicacao.domain.ImovelEntity;
import br.com.domus.aplicacao.domain.dto.CadastroImovelDTO;
import br.com.domus.aplicacao.repository.ImovelRepository;
import br.com.domus.imagem.service.ImageService;

@Service
public class ImovelService {

	@Autowired
	private ImovelRepository imovelRepository;

	@Autowired
	private ImageService imageService;

	public List<ImovelEntity> findAll() {
		return imovelRepository.findAll();
	}

	public ImovelEntity findById(String imovelId) {
		return imovelRepository.findById(imovelId).orElseGet(null);
	}

	public List<ImovelEntity> findByFinalidadeId(String finalidadeId) {
		return imovelRepository.buscarFinalidade(finalidadeId);
	}

	public void createImovel(CadastroImovelDTO cadastroDTO) {
		imovelRepository.save(new ImovelEntity(cadastroDTO));
	}

	public void deleteImovel(String imovelId) {
		ImovelEntity entity = findById(imovelId);

		List<String> urlsImage = new ArrayList<>();

		if (entity.getUrlFotoCard() != null) {
			urlsImage.add(entity.getUrlFotoCard());
		}
		if (entity.getUrlsFotos() != null || !entity.getUrlsFotos().isEmpty()) {
			urlsImage.addAll(entity.getUrlsFotos());
		}
		imovelRepository.delete(entity);

		imageService.deleteImageFiles(urlsImage);
	}

	public void deleteUrlImage(String imovelId, String urlImage) {
		ImovelEntity entity = findById(imovelId);

		if (entity.getUrlFotoCard() != null && entity.getUrlFotoCard().equals(urlImage)) {
			entity.setUrlFotoCard(null);
		}

		if (entity.getUrlsFotos() != null || !entity.getUrlsFotos().isEmpty()) {
			entity.getUrlsFotos().remove(urlImage);
//			entity.getUrlsFotos().removeIf(url -> url.equals(urlImage));
		}

		imovelRepository.save(entity);
	}

	public void updateImovel(String imovelId, CadastroImovelDTO updateDTO) {
		ImovelEntity imovel = findById(imovelId);

		imovel.setTitulo(updateDTO.titulo());
		imovel.setUrlFotoCard(updateDTO.urlFotoCard());
		imovel.setUrlsFotos(updateDTO.urlsFotos());
		imovel.setFinalidadeId(updateDTO.finalidadeId());
		imovel.setTipologiaId(updateDTO.tipologiaId());
		imovel.setRegiaoId(updateDTO.regiaoId());
		imovel.setEndereco(updateDTO.endereco());
		imovel.setQuantidadeQuartos(updateDTO.quantidadeQuartos());
		imovel.setQuantidadeBanheiros(updateDTO.quantidadeBanheiros());
		imovel.setQuantidadeVagas(updateDTO.quantidadeVagas());
		imovel.setAreaQuadrada(updateDTO.areaQuadrada());
		imovel.setDescricaoImovel(updateDTO.descricaoImovel());
		imovel.setValor(updateDTO.valor());
		imovel.setValorCondominio(updateDTO.valorCondominio());
		imovel.setValorIptu(updateDTO.valorIptu());
		imovel.setUrlLocalizacaoMaps(updateDTO.urlLocalizacaoMaps());

		imovelRepository.save(imovel);
	}
}
