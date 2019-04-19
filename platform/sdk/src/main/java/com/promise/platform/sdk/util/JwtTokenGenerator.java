package com.promise.platform.sdk.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.promise.platform.sdk.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

@Component
public class JwtTokenGenerator {
	
	@Value("${self.jwt.secret}")
	private String secret;

	/**
	 * Generates a JWT token containing username as subject, and userId and role as
	 * additional claims. These properties are taken from the specified User object.
	 * Tokens validity is infinite.
	 *
	 * @param u the user for which the token will be generated
	 * @return the JWT token
	 */
	public String generateToken(JwtUser u) {
		final Claims claims = Jwts.claims().setSubject(u.getUsername());
		claims.put("company", u.getCompany());
		claims.put("roles", u.getRoles());
		claims.put("organizations", u.getOrganizations());
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(secret))
				.compact();
	}

}