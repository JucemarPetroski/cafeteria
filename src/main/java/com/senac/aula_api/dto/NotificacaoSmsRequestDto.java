package com.senac.aula_api.dto;

public class NotificacaoSmsRequestDto {
    private String telefoneDestino;
    private String mensagem;

    public NotificacaoSmsRequestDto(String telefoneDestino, String mensagem) {
        this.telefoneDestino = telefoneDestino;
        this.mensagem = mensagem;
    }

    public String getTelefoneDestino() {
        return telefoneDestino;
    }
    public String getMensagem() {
        return mensagem;
    }
}

