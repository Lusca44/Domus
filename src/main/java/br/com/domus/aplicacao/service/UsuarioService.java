package br.com.domus.aplicacao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domus.aplicacao.domain.UsuarioEntity;
import br.com.domus.aplicacao.domain.dto.UsuarioCadastroDTO;
import br.com.domus.aplicacao.domain.dto.UsuarioEditDTO;
import br.com.domus.aplicacao.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

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

	public void desativarAtivarUsuario(String usuarioId) {
		UsuarioEntity usuario = this.findById(usuarioId);
		usuario.setAtivo(!usuario.isAtivo());
		usuario.setDataDesativacao(LocalDate.now());

		usuarioRepository.save(usuario);
	}

	public void editarUsuario(String usuarioId, UsuarioEditDTO usuarioEditDTO) {
		UsuarioEntity usuario = this.findById(usuarioId);

		usuario.setNome(usuarioEditDTO.nome());
		usuario.setEmail(usuarioEditDTO.email());
		usuario.setTelefone(usuarioEditDTO.telefone());

		usuarioRepository.save(usuario);
	}

	public void deleteUsuario(String usuarioId) {
		UsuarioEntity usuario = this.findById(usuarioId);
		usuarioRepository.delete(usuario);
	}
}
