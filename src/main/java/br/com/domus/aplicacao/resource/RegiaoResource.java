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

import br.com.domus.aplicacao.domain.RegiaoEntity;
import br.com.domus.aplicacao.domain.dto.CadastroRegiaoDTO;
import br.com.domus.aplicacao.service.RegiaoService;

@RestController
@RequestMapping("/regiao")
public class RegiaoResource {

	@Autowired
	private RegiaoService regiaoService;

	@GetMapping("/obterTodos")
	public ResponseEntity<List<RegiaoEntity>> findAll() {
		return ResponseEntity.ok(regiaoService.findAll());
	}

	@GetMapping("/obterPorId/{regiaoId}")
	public ResponseEntity<RegiaoEntity> findById(@PathVariable("regiaoId") String regiaoId) {
		return ResponseEntity.ok(regiaoService.findById(regiaoId));
	}

	@PostMapping("/cadastrarRegiao")
	public ResponseEntity<Void> cadastrarRegiao(@RequestBody CadastroRegiaoDTO cadastroRegiao) {
		regiaoService.createRegiao(cadastroRegiao.nomeRegiao());
		return ResponseEntity.ok().build();
	}

	@PutMapping("/updateRegiao/{regiaoId}")
	public ResponseEntity<Void> updateStatus(@PathVariable("regiaoId") String regiaoId, @RequestBody CadastroRegiaoDTO updateDTO) {
		regiaoService.updateRegiao(regiaoId, updateDTO.nomeRegiao());
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/alterarStatus/{regiaoId}")
	public ResponseEntity<Void> alterarStatus(@PathVariable("regiaoId") String regiaoId) {
		regiaoService.alterarStatusDestaqueRegiao(regiaoId);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/deletar/{regiaoId}")
	public ResponseEntity<Void> deletarRegiao(@PathVariable("regiaoId") String regiaoId) {
		regiaoService.deleteRegiao(regiaoId);
		return ResponseEntity.ok().build();
	}

}
