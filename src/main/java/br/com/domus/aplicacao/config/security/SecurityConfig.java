package br.com.domus.aplicacao.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.domus.aplicacao.config.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final String ROLE_ADMIN = "ADMIN";
	private static final String ROLE_USER = "USER";
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //
				.authorizeHttpRequests(authz -> //
				authz.requestMatchers("/api/auth/**").permitAll() //
						.requestMatchers("/lancamento/**").permitAll() //
						.requestMatchers("/finalidade/**").permitAll() //
						.requestMatchers("/imovel/**").permitAll() //
						.requestMatchers("/regiao/**").permitAll() //
						.requestMatchers("/tipologia/**").permitAll() //
						.requestMatchers("/projeto-lancamento/**").permitAll() //
						.requestMatchers("/uploads/**").permitAll() //
						.requestMatchers("/imagem/**").hasAnyRole(ROLE_USER, ROLE_ADMIN) //
						.requestMatchers("/usuario/**").hasAnyRole(ROLE_USER, ROLE_ADMIN) //
						.requestMatchers("/admin/**").hasRole(ROLE_ADMIN).anyRequest().authenticated())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}