package br.com.domus.lancamento.resource;

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

import br.com.domus.lancamento.domain.LancamentoEntity;
import br.com.domus.lancamento.domain.dto.CadastroLancamentoDTO;
import br.com.domus.lancamento.service.LancamentoService;

@RestController
@RequestMapping("/projeto-lancamento")
public class LancamentoResource {

	@Autowired
	private LancamentoService lancamentoService;

	@GetMapping("/obterTodosLancamentos")
	public ResponseEntity<List<LancamentoEntity>> findAll() {
		return ResponseEntity.ok(lancamentoService.findAll());
	}

	@GetMapping("/obterLancamentoPorId/{lancamentoId}")
	public ResponseEntity<LancamentoEntity> findById(@PathVariable("lancamentoId") String lancamentoId) {
		return ResponseEntity.ok(lancamentoService.findById(lancamentoId));
	}

	@PostMapping("/criarLancamento")
	public ResponseEntity<Void> createLancamento(@RequestBody CadastroLancamentoDTO cadastroLancamentoDTO) {
		lancamentoService.createLancamento(cadastroLancamentoDTO);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/updateLancamento/{lancamentoId}")
	public ResponseEntity<Void> updateLancamento(@PathVariable("lancamentoId") String lancamentoId,
			@RequestBody CadastroLancamentoDTO cadastroLancamentoDTO) {
		lancamentoService.updateLancamento(lancamentoId, cadastroLancamentoDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/deleteLancamento/{lancamentoId}")
	public ResponseEntity<Void> deleteLancamento(@PathVariable("lancamentoId") String lancamentoId) {
		lancamentoService.deleteLancamento(lancamentoId);
		return ResponseEntity.ok().build();
	}
}
