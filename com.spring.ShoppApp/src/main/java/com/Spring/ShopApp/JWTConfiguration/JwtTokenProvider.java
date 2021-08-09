package com.Spring.ShopApp.JWTConfiguration;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	private static final Logger logger = LogManager.getLogger(JwtTokenProvider.class);

	@Value
	("${app.jwtExpirationInMs}")
	private int jwtExpirationInMs;
	

	@Value
	("${app.jwtSecret}")
	private String jwtSecret;

	public String generateToken(Authentication authentication) {

		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

		Date now = new Date();
		Date expiry = new Date(now.getTime() + jwtExpirationInMs);

		return Jwts.builder().setSubject(Long.toString(userPrincipal.getId())).setIssuedAt(new Date()) .setExpiration(expiry).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return Long.parseLong(claims.getSubject());
	}

	public boolean validateToken(String authors) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authors);
			return true;
		}
		catch (MalformedJwtException ex) {
			logger.error("MalformedJwtException");
		}
		catch (SignatureException ex) {
			logger.error("SignatureException");
		}
		catch (ExpiredJwtException ex) {
			logger.error("ExpiredJwtException");
		} 
		catch (IllegalArgumentException ex) {
			logger.error("IllegalArgumentException");
		}
		catch (UnsupportedJwtException ex) {
			logger.error("UnsupportedJwtException");
		}

		return false;
	}
}
