package br.com.domus.aplicacao.config.security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.domus.aplicacao.config.security.CustomUserDetailsService;

@Configuration
public class JwtFilterConfig {

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter(JwtTokenProvider tokenProvider,
			CustomUserDetailsService customUserDetailsService) {
		return new JwtAuthenticationFilter(tokenProvider, customUserDetailsService);
	}
}
