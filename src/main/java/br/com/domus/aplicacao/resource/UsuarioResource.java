package br.com.domus.aplicacao.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.domus.aplicacao.domain.UsuarioEntity;
import br.com.domus.aplicacao.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/obterUsuarios")
	public ResponseEntity<List<UsuarioEntity>> findAll() {
		return ResponseEntity.ok().body(usuarioService.findAll());
	}

	@GetMapping("/obterUsuario/{usuarioId}")
	public ResponseEntity<UsuarioEntity> findById(@PathVariable("usuarioId") String usuarioId) {
		return ResponseEntity.ok().body(usuarioService.findById(usuarioId));
	}

//	@PostMapping("/cadastroUsuario")
//	public ResponseEntity<Void> createUsuario(@RequestBody UsuarioCadastroDTO usuarioDTO) {
//		usuarioService.createUsuario(usuarioDTO);
//		return ResponseEntity.ok().build();
//	}

	@PutMapping("/desativarUsuario/{usuarioId}")
	public ResponseEntity<Void> desativarUsuario(@PathVariable("usuarioId") String usuarioId) {
		usuarioService.desativarUsuario(usuarioId);
		return ResponseEntity.ok().build();
	}

}
