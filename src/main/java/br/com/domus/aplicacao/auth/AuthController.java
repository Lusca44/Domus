package br.com.domus.aplicacao.auth;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.domus.aplicacao.config.security.CustomUserDetails;
import br.com.domus.aplicacao.config.security.jwt.JwtTokenProvider;
import br.com.domus.aplicacao.domain.UsuarioEntity;
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
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Obtém o CustomUserDetails do usuário autenticado
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		String token = tokenProvider.generateToken(userDetails.getUsername(), userDetails.getIsAdmin());

		return ResponseEntity.ok(new AuthResponse(token));
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
		if (usuarioRepository.existsByEmail(registerRequest.getEmail())) {
			return ResponseEntity.badRequest().body("Email já está em uso");
		}

		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNome(registerRequest.getNome());
		usuario.setEmail(registerRequest.getEmail());
		usuario.setSenha(passwordEncoder.encode(registerRequest.getPassword()));
		usuario.setTelefone(registerRequest.getTelefone());
		usuario.setIsAdmin("N"); // Por padrão, não é admin
		usuario.setAtivo(true);
		usuario.setDataCadastro(LocalDate.now());

		usuarioRepository.save(usuario);

		return ResponseEntity.ok("Usuário registrado com sucesso");
	}

	// Classes internas para requests e responses
	public static class LoginRequest {
		private String email;
		private String password;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

	public static class RegisterRequest {
		private String nome;
		private String email;
		private String password;
		private String telefone;

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

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
	}

	public static class AuthResponse {
		private String token;

		public AuthResponse(String token) {
			this.token = token;
		}

		public String getToken() {
			return token;
		}
	}
}