package br.com.domus.aplicacao.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.domus.aplicacao.config.security.CustomUserDetails;
import br.com.domus.aplicacao.config.security.jwt.JwtTokenProvider;
import br.com.domus.aplicacao.domain.UsuarioEntity;
import br.com.domus.aplicacao.domain.dto.UsuarioResponse;
import br.com.domus.aplicacao.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider tokenProvider;
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;

	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider,
			UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		String token = tokenProvider.generateToken(userDetails.getUsername(), userDetails.getIsAdmin());

		UsuarioEntity usuarioEntity = usuarioRepository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		UsuarioResponse usuario = new UsuarioResponse(usuarioEntity.getId(), usuarioEntity.getNome(),
				usuarioEntity.getEmail(), usuarioEntity.getDataCadastro(), usuarioEntity.isAtivo(),
				usuarioEntity.getTelefone(), usuarioEntity.isAdmin());

		return ResponseEntity.ok(new LoginResponse(token, usuario));
	}

	@PostMapping("/cadastroUsuario")
	public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
		if (usuarioRepository.existsByEmail(registerRequest.getEmail())) {
			return ResponseEntity.badRequest().body("Email já está em uso");
		}

		if (registerRequest.getSenha() == null || registerRequest.getSenha().isEmpty()) {
			return ResponseEntity.badRequest().body("A senha é obrigatória");
		}

		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNome(registerRequest.getNome());
		usuario.setEmail(registerRequest.getEmail());
		usuario.setSenha(passwordEncoder.encode(registerRequest.getSenha()));
		usuario.setTelefone(registerRequest.getTelefone());
		usuario.setAdmin(registerRequest.isAdmin());
		usuario.setAtivo(true);
		usuario.setDataCadastro(LocalDate.now());

		usuarioRepository.save(usuario);

		return ResponseEntity.ok("Usuário registrado com sucesso");
	}

	@PutMapping("/alterarSenha")
	public ResponseEntity<String> editarSenha(@RequestBody EditarSenhaDTO editarSenhaDTO) {
		return usuarioRepository.findByEmail(editarSenhaDTO.email()).map(
				usuario -> this.validarSenhaEAtualizar(usuario, editarSenhaDTO.senha(), editarSenhaDTO.senhaNova()))
				.orElseGet(() -> ResponseEntity.status(NOT_FOUND).body("Usuario não encontrado!"));
	}

	private ResponseEntity<String> validarSenhaEAtualizar(UsuarioEntity usuario, String senha, String senhaNova) {
		if (!passwordEncoder.matches(senha ,usuario.getSenha())) {
			return ResponseEntity.status(UNAUTHORIZED).body("Senha atual incorreta!");
		} else {
			if (senha.equalsIgnoreCase(senhaNova)) {
				return ResponseEntity.status(BAD_REQUEST).body("Senha nova não pode ser igual a senha atual!");
			}

			usuario.setSenha(passwordEncoder.encode(senhaNova));
			usuarioRepository.save(usuario);
			return ResponseEntity.ok("Senha atualizada!");
		}
	}

	// Classes internas para requests e responses
	public static class LoginRequest {
		private String email;
		private String senha;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}
	}

	public static class RegisterRequest {
		private String nome;
		private String email;
		private String senha;
		private String telefone;
		private boolean isAdmin;

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}

		public boolean isAdmin() {
			return isAdmin;
		}

		public void setIsAdmin(boolean isAdmin) {
			this.isAdmin = isAdmin;
		}
	}
}