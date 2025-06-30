package com.electromart.restapi.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private final String SECRET_KEY = "secretkey123456";

	private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

	public String generateToken(String username) {
		return Jwts.builder()//
				.setSubject(username)//
				.setIssuedAt(new Date(System.currentTimeMillis()))//
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))//
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)//
				.compact();
	}

	public boolean validateToken(String token, String username) {
		final var tokenUsername = extractUsername(token);
		return (tokenUsername.equals(username) && !isTokenExpired(token));
	}

	public String extractUsername(String token) {
		return Jwts.parser()//
				.setSigningKey(SECRET_KEY)//
				.parseClaimsJws(token)//
				.getBody()//
				.getSubject();
	}

	public boolean isTokenExpired(String token) {
		final var expiration = Jwts.parser()//
				.setSigningKey(SECRET_KEY)//
				.parseClaimsJws(token)//
				.getBody()//
				.getExpiration();
		return expiration.before(new Date());
	}
}
