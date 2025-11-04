package com.senac.aula_api.application.dto;

public class NotificacaoEmailRequestDto {
    private String emailDestino;
    private String mensagem;

    public String getEmailDestino() {
        return emailDestino;
    }
    public void setEmailDestino(String emailDestino) {
        this.emailDestino = emailDestino;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
