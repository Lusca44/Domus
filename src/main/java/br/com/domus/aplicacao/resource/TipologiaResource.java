package br.com.domus.aplicacao.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.domus.aplicacao.domain.TipologiaEntity;
import br.com.domus.aplicacao.service.TipologiaService;

@RestController
@RequestMapping("/tipologia")
public class TipologiaResource {

	@Autowired
	private TipologiaService tipologiaService;

	@GetMapping("/obterTodos")
	public ResponseEntity<List<TipologiaEntity>> findAll() {
		return ResponseEntity.ok(tipologiaService.findAll());
	}

	@GetMapping("/obterPorId/{tipologiaId}")
	public ResponseEntity<TipologiaEntity> findById(@PathVariable("tipologiaId") String tipologiaId) {
		return ResponseEntity.ok(tipologiaService.findById(tipologiaId));
	}

	@PostMapping("/criarTipologia")
	public ResponseEntity<Void> createTipologia(@RequestBody String nomeTipologia) {
		tipologiaService.createTipologia(nomeTipologia);
		return ResponseEntity.ok().build();
	}
}
