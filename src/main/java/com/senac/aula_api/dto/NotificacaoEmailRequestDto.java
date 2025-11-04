package com.senac.aula_api.dto;

public class NotificacaoEmailRequestDto {
    private String emailDestino;
    private String mensagem;

    public NotificacaoEmailRequestDto(String emailDestino, String mensagem) {
        this.emailDestino = emailDestino;
        this.mensagem = mensagem;
    }

    public String getEmailDestino() {
        return emailDestino;
    }
    public String getMensagem() {
        return mensagem;
    }
}

