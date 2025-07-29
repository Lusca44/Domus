package br.com.domus.aplicacao.resource;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.domus.aplicacao.domain.FinalidadeEntity;
import br.com.domus.aplicacao.domain.dto.CadastroFinalidadeDTO;
import br.com.domus.aplicacao.service.FinalidadeService;

@RestController
@RequestMapping("/finalidade")
public class FinalidadeResource {

	@Autowired
	private FinalidadeService finalidadeService;

	@GetMapping("/obterTodasFinalidades")
	public ResponseEntity<List<FinalidadeEntity>> findAll() {
		return ok(finalidadeService.findAll());
	}

	@GetMapping("/obterFinalidadePorId/{finalidadeId}")
	public ResponseEntity<FinalidadeEntity> findById(@PathVariable("finalidadeId") String finalidadeId) {
		return ok(finalidadeService.findById(finalidadeId));
	}

	@PostMapping("/cadastrarFinalidade")
	public ResponseEntity<Void> cadastrarFinalidade(@RequestBody CadastroFinalidadeDTO cadastroFinalidade) {
		finalidadeService.createFinalidade(cadastroFinalidade.nomeFinalidade());
		return ok().build();
	}
}
