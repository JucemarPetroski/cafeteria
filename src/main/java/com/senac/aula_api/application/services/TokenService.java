package com.senac.aula_api.application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.senac.aula_api.application.dto.LoginRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Value("${spring.minhapalavra}")
    private String secret;

    private String emissor = "Jucemar";

    @Value("${spring.sessao}")
    private Long tempo;
    
    public String generateToken(LoginRequestDto login) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer(emissor)
                    .withSubject(login.email())
                    .withExpiresAt(this.gerarDataExpiracao())
                    .sign(algorithm);

            return token;
        } catch (Exception e) {
            logger.error("Erro ao gerar token JWT", e);
            return null;
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(emissor)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Token inválido", e);
        }
    }

    private Instant gerarDataExpiracao() {
        var dataAtual = LocalDateTime.now();
        var dataFutura = dataAtual.plusMinutes(tempo);
        return dataFutura.toInstant(ZoneOffset.of("-03:00"));
    }
}