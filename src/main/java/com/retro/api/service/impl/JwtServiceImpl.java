package com.retro.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.retro.api.service.JwtService;
import com.retro.api.service.enums.TokenType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.access.key}")
    private String ACCESS_KEY;

    @Value("${jwt.refresh.key}")
    private String REFRESH_KEY;

    @Value("${jwt.access.expiration}")
    private long ACCESS_EXPIRATION;

    @Value("${jwt.refresh.expiration}")
    private long REFRESH_EXPIRATION;

    @Value("${issuer}")
    private String ISSUER;

    @Override
    public String generaToken(TokenType type, String username, List<String> roles) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(username)
                .withClaim("roles", roles)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(getExpiration(type)))
                .sign(Algorithm.HMAC256(getSecretKey(type)));
    }

    @Override
    public DecodedJWT verifyToken(TokenType type, String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(getSecretKey(type)))
                .withIssuer(ISSUER)
                .build();

        return verifier.verify(token);
    }

    private String getSecretKey(TokenType type) {
        if (type == TokenType.ACCESS) {
            return ACCESS_KEY;
        } else {
            return REFRESH_KEY;
        }
    }

    private long getExpiration(TokenType type) {
        if (type == TokenType.ACCESS) {
            return ACCESS_EXPIRATION;
        } else {
            return REFRESH_EXPIRATION;
        }
    }
}
