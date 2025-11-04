package com.senac.aula_api.application.dto;

public record UsuarioCriarRequestDto(String nome, String email, String senha, String cpf) {
}
