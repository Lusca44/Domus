package br.com.domus.lancamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domus.lancamento.domain.LeadLancamentoEntity;
import br.com.domus.lancamento.domain.dto.LeadLancamentoDTO;
import br.com.domus.lancamento.domain.subdomain.DetalhesClienteLead;
import br.com.domus.lancamento.domain.subdomain.dto.DetalhesClienteLeadDTO;
import br.com.domus.lancamento.repository.LeadLancamentoRepository;

@Service
public class LeadLancamentoService {

	@Autowired
	private LeadLancamentoRepository repository;

	public List<LeadLancamentoEntity> findAll() {
		return repository.findAll();
	}

	public LeadLancamentoEntity findById(String leadId) {
		return repository.findById(leadId).orElse(null);
	}

	public List<LeadLancamentoEntity> findByNomeLancamento(String nomeLancamento) {
		return repository.findByNomeLancamento(nomeLancamento);
	}

	public List<LeadLancamentoEntity> findAllLancamentosPorCorretorOpcionistaId(String corretorOpcionistaId) {
		return repository.findAllByCorretorOpcionistaId(corretorOpcionistaId);
	}

	public void createLeadLancamento(LeadLancamentoDTO leadLancamentoDTO) {
		repository.save(new LeadLancamentoEntity(leadLancamentoDTO));
	}

	public void insertDetalhesClienteLead(String leadId, DetalhesClienteLeadDTO detalhesClienteDTO) {
		LeadLancamentoEntity leadLancamento = repository.findById(leadId).orElseThrow();

		DetalhesClienteLead detalhesLead = new DetalhesClienteLead(detalhesClienteDTO);
		leadLancamento.setDetalhesCliente(detalhesLead);
		repository.save(leadLancamento);
	}

	public void insertUsuarioOpcionistaLead(String leadId, String usuarioId) {
		LeadLancamentoEntity leadLancamento = repository.findById(leadId).orElseThrow();
		leadLancamento.setUsuarioOpcionistaId(usuarioId);
		repository.save(leadLancamento);
	}

	public void deleteUsuarioOpcionistaLead(String usuarioId) {
		List<LeadLancamentoEntity> leadsLancamentos = findAllLancamentosPorCorretorOpcionistaId(usuarioId);

		leadsLancamentos.stream().filter(lead -> !lead.isLancamentoConcluido())
				.forEach(lead -> lead.setUsuarioOpcionistaId(null));
		repository.saveAll(leadsLancamentos);
	}
}
