package com.senac.aula_api.presentation;

import com.senac.aula_api.application.dto.LoginRequestDto;
import com.senac.aula_api.application.dto.LoginResponseDto;
import com.senac.aula_api.application.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação controller", description = "Gerenciamento de autenticações!")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private com.senac.aula_api.domain.repository.UsuarioRepository usuarioRepository;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @Operation(summary = "Autenticação de usuário", description = "Método para fazer autenticação")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {

        // Autenticação real: buscar usuário por email e comparar senha
        var usuarioOpt = usuarioRepository.findByEmail(loginRequestDto.email());
        if (usuarioOpt.isEmpty()) {
            logger.warn("Autenticação falhou: usuário não encontrado para email={}", loginRequestDto.email());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
        }
        var usuario = usuarioOpt.get();
        // verificar status
        if (usuario.getStatus() != null && usuario.getStatus().toString().equalsIgnoreCase("INATIVO")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean matches = passwordEncoder.matches(loginRequestDto.senha(), usuario.getSenha());
        if (!matches) {
            logger.warn("Autenticação falhou: senha inválida para email={}", loginRequestDto.email());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

        // gerar token para o usuário autenticado
        var token = tokenService.generateToken(new com.senac.aula_api.application.dto.LoginRequestDto(usuario.getEmail(), usuario.getSenha()));
        if (token == null) {
            logger.error("TokenService retornou null para usuario={}", usuario.getEmail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao gerar token");
        }
        logger.info("Token gerado com sucesso para usuario={}", usuario.getEmail());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}