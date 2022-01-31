package com.burak.apigw.util;

import com.burak.apigw.exception.JwtTokenMalformedException;
import com.burak.apigw.exception.JwtTokenMissingException;
import com.burak.clients.customer.CustomerClient;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

	@Value("${jwt.secretKey}")
	private String secretKey;

	@Value("${jwt.tokenPrefix}")
	private String tokenPrefix;

	public Claims getClaims(final String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
			return body;
		} catch (Exception e) {
			System.out.println(e.getMessage() + " => " + e);
		}
		return null;
	}

	public String generateToken(String email) {

		Claims claims = Jwts.claims().setSubject(email);
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + 180000;
		Date exp = new Date(expMillis);
		return getTokenPrefix() + " " + Jwts.builder().setClaims(claims)
				.setIssuedAt(new Date(nowMillis))
				.setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512, getSecretKey()).compact();
	}

	public void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException {
		try {
			Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token);
		} catch (SignatureException ex) {
			throw new JwtTokenMalformedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalformedException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalformedException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty.");
		}
	}

	private String getSecretKey(){
		return secretKey;
	}

	private String getTokenPrefix(){
		return tokenPrefix;
	}

}
