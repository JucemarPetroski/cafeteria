package com.senac.aula_api.application.services;

import com.senac.aula_api.application.dto.LoginRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class TokenServiceTest {

    @Test
    public void gerarEValidarToken_deveRetornarSubject() throws Exception {
        TokenService tokenService = new TokenService();

        // configurar campos privados via reflexão (simula @Value)
        Field secretField = TokenService.class.getDeclaredField("secret");
        secretField.setAccessible(true);
        secretField.set(tokenService, "segredo_teste_123456");

        Field tempoField = TokenService.class.getDeclaredField("tempo");
        tempoField.setAccessible(true);
        tempoField.set(tokenService, 60L);

        Field emissorField = TokenService.class.getDeclaredField("emissor");
        emissorField.setAccessible(true);
        emissorField.set(tokenService, "Jucemar");

        // criar DTO de login
        String email = "teste@exemplo.com";
        LoginRequestDto dto = new LoginRequestDto(email, "senha");

        String token = tokenService.generateToken(dto);
        Assertions.assertNotNull(token, "Token não deve ser nulo");

        String subject = tokenService.validarToken(token);
        Assertions.assertEquals(email, subject, "Subject do token deve ser o email");
    }
}
