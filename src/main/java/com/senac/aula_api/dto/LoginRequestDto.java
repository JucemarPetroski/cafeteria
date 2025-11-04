package com.senac.aula_api.dto;

public class LoginRequestDto {
    private String email;
    private String senha;

    public LoginRequestDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }
}

