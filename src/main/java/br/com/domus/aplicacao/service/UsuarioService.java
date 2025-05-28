package br.com.domus.aplicacao.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domus.aplicacao.domain.UsuarioEntity;
import br.com.domus.aplicacao.domain.dto.UsuarioCadastroDTO;
import br.com.domus.aplicacao.repository.UsuarioRepository;
import br.com.domus.lancamento.service.LeadLancamentoService;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private LeadLancamentoService leadLancamentoService;

	public List<UsuarioEntity> findAll() {
		return usuarioRepository.findAll();
	}

	public UsuarioEntity findById(String usuarioId) {
		Optional<UsuarioEntity> usuario = usuarioRepository.findById(usuarioId);
		return usuario.orElseThrow(NoSuchElementException::new);
	}

	public void createUsuario(UsuarioCadastroDTO usuarioDTO) {
		UsuarioEntity usuario = new UsuarioEntity(usuarioDTO);
		usuarioRepository.save(usuario);
	}

	public void desativarUsuario(String usuarioId) {
		UsuarioEntity usuario = this.findById(usuarioId);
		usuario.setAtivo(false);

		leadLancamentoService.deleteUsuarioOpcionistaLead(usuarioId);
		usuarioRepository.save(usuario);
	}
}
