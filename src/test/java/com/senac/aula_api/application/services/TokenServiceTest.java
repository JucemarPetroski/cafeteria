package com.senac.aula_api.application.services;

import com.senac.aula_api.application.dto.LoginRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class TokenServiceTest {

    @Test
    public void gerarEValidarToken_deveRetornarSubject() throws Exception {
        TokenService tokenService = new TokenService();

        // configurar campo privado via reflexão (simula @Value)
        Field secretField = TokenService.class.getDeclaredField("secretKey");
        secretField.setAccessible(true);
        secretField.set(tokenService, "segredo_teste_12345678901234567890123456789012");

        // criar DTO de login
        String email = "teste@exemplo.com";
        LoginRequestDto dto = new LoginRequestDto(email, "senha");

        String token = tokenService.generateToken(email);
        Assertions.assertNotNull(token, "Token não deve ser nulo");

        String subject = tokenService.getSubject(token);
        Assertions.assertEquals(email, subject, "Subject do token deve ser o email");
    }
}
