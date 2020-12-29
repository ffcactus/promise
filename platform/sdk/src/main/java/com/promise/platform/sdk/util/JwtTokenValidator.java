package com.promise.platform.sdk.util;

import com.promise.platform.sdk.exception.UnauthorizedException;
import com.promise.platform.sdk.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtTokenValidator {

    @Value("${self.jwt.secret}")
    private String secret;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User
     * object with username, company, roles and organizations filled (extracted from
     * token). If unsuccessful (token is invalid or not containing all required user
     * properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is
     * invalid.
     */
    @SuppressWarnings("unchecked")
    public JwtUser parseToken(String token) {
        Claims body = null;
        try {
            body = Jwts.parser().setSigningKey(TextCodec.BASE64.decode(secret)).parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new UnauthorizedException();
        }

        // @formatter:off
        return new JwtUser(
                Long.valueOf(body.get("userId").toString()),
                body.getSubject(),
                String.valueOf(body.get("company")),
                (List<String>) body.get("roles"),
                (List<String>) body.get("organizations"));

    }

}