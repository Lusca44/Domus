package br.com.domus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Permitir localhost:8081 (seu frontend)
        config.addAllowedOrigin("https://domuns-front-lucas-santos-projects-d53d8b43.vercel.app");
        config.addAllowedOrigin("https://domuns-front.vercel.app");
        
        // Permitir métodos necessários
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        config.addAllowedMethod("OPTIONS"); // Importante para preflight
        
        // Permitir headers necessários
        config.addAllowedHeader("*");
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("Content-Type");
        config.addAllowedHeader("Accept");
        
        config.setAllowCredentials(true); // Permitir credenciais (JWT)
        source.registerCorsConfiguration("/**", config); // Aplicar a todas as rotas
        
        return new CorsFilter(source);
    }
}
