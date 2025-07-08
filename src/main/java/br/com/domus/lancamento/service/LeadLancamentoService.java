package br.com.domus.lancamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domus.lancamento.domain.LeadLancamentoEntity;
import br.com.domus.lancamento.domain.dto.LeadLancamentoDTO;
import br.com.domus.lancamento.domain.dto.UpdateLeadLancamentoDTO;
import br.com.domus.lancamento.domain.subdomain.DetalhesClienteLead;
import br.com.domus.lancamento.domain.subdomain.dto.DetalhesClienteLeadDTO;
import br.com.domus.lancamento.repository.LeadLancamentoRepository;
import br.com.domus.lancamento.resource.InserirCorretorLoteDTO;

@Service
public class LeadLancamentoService {

	@Autowired
	private LeadLancamentoRepository repository;

	public List<LeadLancamentoEntity> findAll() {
		return repository.findAll();
	}

	public List<LeadLancamentoEntity> findAll(QueryBuscaLancamentos query) {
		return repository.buscarPorFiltros(query);
	}

	public LeadLancamentoEntity findById(String leadId) {
		return repository.findById(leadId).orElse(null);
	}

	public List<LeadLancamentoEntity> findByNomeLancamento(String nomeLancamento) {
		return repository.findByNomeLancamento(nomeLancamento);
	}

	public List<LeadLancamentoEntity> findAllLancamentosPorCorretorOpcionistaId(String corretorOpcionistaId,
			QueryBuscaLancamentos query) {
		return repository.findAllByCorretorOpcionistaId(corretorOpcionistaId);
	}

	public void createLeadLancamento(LeadLancamentoDTO leadLancamentoDTO) {
		repository.save(new LeadLancamentoEntity(leadLancamentoDTO));
	}

	public void updateLead(String idLead, UpdateLeadLancamentoDTO uptadeLeadLancamentoDTO) {
		LeadLancamentoEntity lead = this.findById(idLead);

		lead.setNomeCliente(uptadeLeadLancamentoDTO.nomeCliente());
		lead.setNomeLancamento(uptadeLeadLancamentoDTO.nomeLancamento());
		lead.setTelefoneCliente(uptadeLeadLancamentoDTO.telefoneCliente());
		lead.setUsuarioOpcionistaId(uptadeLeadLancamentoDTO.usuarioOpcionista());

		repository.save(lead);
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

	public void insertUsuarioOpcionistaLeadLote(InserirCorretorLoteDTO loteDTO) {
		for (String leadId : loteDTO.leadIds()) {
			insertUsuarioOpcionistaLead(leadId, loteDTO.corretorId());
		}
	}

	public void delete(String leadId) {
		repository.deleteById(leadId);
	}
}
