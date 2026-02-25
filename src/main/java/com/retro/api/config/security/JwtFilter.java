package com.retro.api.config.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.retro.api.service.JwtService;
import com.retro.api.service.enums.TokenType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        logger.info("Jwt filter");
        String jwtToken = authorization.substring(7);

        DecodedJWT tokenDecode;
        UserDetails userDetails;

        try {
             tokenDecode = jwtService.verifyToken(TokenType.ACCESS, jwtToken);
             userDetails = userDetailsService.loadUserByUsername(tokenDecode.getSubject());
             if (!userDetails.isAccountNonLocked()) {
                 throw new AuthenticationException("The account has been locked");
             }
        } catch (Exception e) {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().print("{\"error\":\"Unauthorized\"}");
            return;
        }

        SecurityContext context = SecurityContextHolder.createEmptyContext();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                tokenDecode.getClaim("roles").asList(String.class)
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList()
        );

        authentication.eraseCredentials();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        filterChain.doFilter(request, response);
    }
}
