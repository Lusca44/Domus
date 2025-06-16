package br.com.domus.aplicacao.config.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private int jwtExpirationMs;

	private SecretKey getSigningKey() {
		try {
			byte[] keyBytes = Decoders.BASE64URL.decode(jwtSecret);
			return Keys.hmacShaKeyFor(keyBytes);
		} catch (Exception e) {
			try {
				byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
				return Keys.hmacShaKeyFor(keyBytes);
			} catch (Exception ex) {
				throw new RuntimeException("Chave JWT inválida", ex);
			}
		}
	}

	public String generateToken(String email, boolean isAdmin) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

		return Jwts.builder().subject(email).claim("isAdmin", isAdmin).issuedAt(now).expiration(expiryDate)
				.signWith(getSigningKey()).compact();
	}

	public String getEmailFromToken(String token) {
		Claims claims = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
		return claims.getSubject();
	}

	public String getIsAdminFromToken(String token) {
		Claims claims = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
		return claims.get("isAdmin", String.class);
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}