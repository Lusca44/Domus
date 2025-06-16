package br.com.domus.aplicacao.config.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.domus.aplicacao.domain.UsuarioEntity;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 2697097957910013039L;

	private final UsuarioEntity usuario;

	public CustomUserDetails(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Se o usuário é admin, retorna a role ADMIN
		if (usuario.isAdmin()) {
			return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return usuario.isAtivo();
	}

	public boolean getIsAdmin() {
		return usuario.isAdmin();
	}
}
