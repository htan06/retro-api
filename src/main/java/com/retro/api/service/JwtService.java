package com.retro.api.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.retro.api.service.enums.TokenType;

import java.util.List;

public interface JwtService {
    String generaToken(TokenType type, String username, List<String> roles);

    DecodedJWT verifyToken(TokenType type, String token);
}
