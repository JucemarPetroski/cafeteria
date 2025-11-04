package com.senac.aula_api.application.dto.usuario;

public class UsuarioResponseDto {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String status;

    public UsuarioResponseDto(Long id, String nome, String cpf, String email, String telefone, String status) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getStatus() {
        return status;
    }
}
