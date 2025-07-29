package br.com.domus.aplicacao.resource;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.domus.aplicacao.domain.ImovelEntity;
import br.com.domus.aplicacao.domain.dto.CadastroImovelDTO;
import br.com.domus.aplicacao.service.ImovelService;

@RestController
@RequestMapping("/imovel")
public class ImovelResource {

	@Autowired
	private ImovelService imovelService;

	@GetMapping("/obterTodosImoveis")
	public ResponseEntity<List<ImovelEntity>> findAll() {
		return ok(imovelService.findAll());
	}

	@GetMapping("/obterImovel/{imovelId}")
	public ResponseEntity<ImovelEntity> findById(@PathVariable("imovelId") String imovelId) {
		return ok(imovelService.findById(imovelId));
	}

	@GetMapping("/obterImoveisPorFinalidade/{finalidadeId}")
	public ResponseEntity<List<ImovelEntity>> findByFinalidadeId(@PathVariable("finalidadeId") String finalidadeId) {
		return ok(imovelService.findByFinalidadeId(finalidadeId));
	}

	@PostMapping("/cadastrarImovel")
	public ResponseEntity<Void> cadastrarImovel(@RequestBody CadastroImovelDTO cadastroImovelDTO) {
		imovelService.createImovel(cadastroImovelDTO);
		return ok().build();
	}

	@PutMapping("/updateImovel/{imovelId}")
	public ResponseEntity<Void> updateImovel(@PathVariable("imovelId") String imovelId,
			@RequestBody CadastroImovelDTO cadastroImovelDTO) {
		imovelService.updateImovel(imovelId, cadastroImovelDTO);
		return ok().build();
	}

	@DeleteMapping("/deleteImovel/{imovelId}")
	public ResponseEntity<Void> deleteImovel(@PathVariable("imovelId") String imovelId) {
		imovelService.deleteImovel(imovelId);
		return ok().build();
	}
}
