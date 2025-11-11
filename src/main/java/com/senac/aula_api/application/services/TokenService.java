package com.senac.aula_api.application.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    // Chave secreta vem do application.properties
    @Value("${api.security.token.secret}")
    private String secretKey;

    // Tempo de expiração (em milissegundos) — 24 horas
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

    /**
     * Gera um token JWT baseado no e-mail do usuário autenticado.
     */
    public String generateToken(String email) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(email) // identificador do usuário
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrai o e-mail (subject) de um token JWT.
     */
    public String getSubject(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * Verifica se o token é válido e não expirou.
     */
    public boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            Date expiration = claims.getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Valida o token e retorna o subject (e-mail).
     */
    public String validarToken(String token) {
        return getSubject(token);
    }
}
