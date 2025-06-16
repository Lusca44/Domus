package br.com.domus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import br.com.domus.aplicacao.config.security.CustomUserDetailsService;
import br.com.domus.aplicacao.config.security.jwt.JwtAuthenticationFilter;
import br.com.domus.aplicacao.config.security.jwt.JwtTokenProvider;

@SpringBootTest
@ActiveProfiles("test")
public class DomusApplicationTests {

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @MockBean
    private JwtTokenProvider jwtTokenProvider;
    
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void contextLoads() {
        // Teste deve passar agora
    }
}
