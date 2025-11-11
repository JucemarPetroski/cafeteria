package com.senac.aula_api.presentation;

import com.senac.aula_api.application.dto.LoginRequestDto;
import com.senac.aula_api.application.dto.LoginResponseDto;
import com.senac.aula_api.application.services.TokenService;
import com.senac.aula_api.domain.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação controller", description = "Gerenciamento de autenticações!")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private com.senac.aula_api.application.services.UsuarioService usuarioService;

    @PostMapping("/login")
    @Operation(summary = "Autenticação de usuário", description = "Método para autenticação de usuários do sistema")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {

        // Autenticar usuário
        String email = usuarioService.autenticarUsuario(loginRequestDto.email(), loginRequestDto.senha());
        if (email == null) {
            logger.warn("Falha na autenticação para ({})", loginRequestDto.email());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

        // Gerar token JWT seguro
        var token = tokenService.generateToken(email);
        if (token == null) {
            logger.error("Erro interno: TokenService retornou null para ({})", email);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao gerar token");
        }

        logger.info("Usuário autenticado com sucesso: {}", email);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
