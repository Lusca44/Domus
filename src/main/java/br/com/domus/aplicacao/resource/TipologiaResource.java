package br.com.domus.aplicacao.resource;

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

import br.com.domus.aplicacao.domain.TipologiaEntity;
import br.com.domus.aplicacao.domain.dto.CadastroTipologiaDTO;
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
	public ResponseEntity<Void> createTipologia(@RequestBody CadastroTipologiaDTO cadastroTipologia) {
		tipologiaService.createTipologia(cadastroTipologia.nomeTipologia());
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/updateTipologia/{idTipologia}")
	public ResponseEntity<Void> updateTipologia(@PathVariable("idTipologia") String idTipologia, @RequestBody CadastroTipologiaDTO cadastroTipologia) {
		tipologiaService.updateTipologia(idTipologia, cadastroTipologia.nomeTipologia());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/delteTipologia/{idTipologia}")
	public ResponseEntity<Void> deleteTipologia(@PathVariable("idTipologia") String idTipologia) {
		tipologiaService.deleteTipologia(idTipologia);
		return ResponseEntity.ok().build();
	}
}
