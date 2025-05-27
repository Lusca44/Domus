package br.com.domus.lancamento.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.domus.lancamento.domain.LeadLancamentoEntity;
import br.com.domus.lancamento.domain.dto.LeadLancamentoDTO;
import br.com.domus.lancamento.domain.subdomain.dto.DetalhesClienteLeadDTO;
import br.com.domus.lancamento.service.LeadLancamentoService;

@RestController
@RequestMapping("/lancamento")
public class LeadLancamentoResource {

	@Autowired
	private LeadLancamentoService leadLancamentoService;

	@PostMapping(value = "/cadastroLead")
	public ResponseEntity<Void> createLeadLancamento(@RequestBody LeadLancamentoDTO leadLancamentoDTO) {
		leadLancamentoService.createLeadLancamento(leadLancamentoDTO);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/obterLead/{id}")
	public ResponseEntity<LeadLancamentoEntity> findLeadById(@PathVariable("id") String id) {
		return ResponseEntity.ok().body(leadLancamentoService.findById(id));
	}

	@GetMapping(value = "/obterLeads")
	public ResponseEntity<List<LeadLancamentoEntity>> findAllLeadsLancamento() {
		return ResponseEntity.ok().body(leadLancamentoService.findAll());
	}

	@GetMapping(value = "obterLeads/{nomeProjeto}")
	public ResponseEntity<List<LeadLancamentoEntity>> findAllLeadsPorNomeLancamento(
			@PathVariable("nomeProjeto") String nomeProjeto) {
		return ResponseEntity.ok().body(leadLancamentoService.findByNomeLancamento(nomeProjeto));
	}

	@PutMapping(value = "inserirDetalhes/{idLead}")
	public ResponseEntity<Void> insertDetalhesClienteLead(@PathVariable("idLead") String idLead,
			@RequestBody DetalhesClienteLeadDTO detalhesLeadDTO) {
		leadLancamentoService.insertDetalhesClienteLead(idLead, detalhesLeadDTO);
		return ResponseEntity.ok().build();
	}
}
