package com.promise.platform.auth.sdk.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtTokenGenerator {
    private static final long EXPIRATION_MINUTES = 30;

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
        claims.put("userId", u.getId());
        claims.put("company", u.getCompany());
        claims.put("roles", u.getRoles());
        claims.put("organizations", u.getOrganizations());
        claims.setExpiration(getExpiration());

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(secret))
                .compact();
    }

    private Date getExpiration() {
        Instant expiration = LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(expiration);
    }

}