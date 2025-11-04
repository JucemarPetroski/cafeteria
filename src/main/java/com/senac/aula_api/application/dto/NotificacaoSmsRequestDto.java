package com.senac.aula_api.application.dto;

public class NotificacaoSmsRequestDto {
    private String telefoneDestino;
    private String mensagem;

    public String getTelefoneDestino() {
        return telefoneDestino;
    }
    public void setTelefoneDestino(String telefoneDestino) {
        this.telefoneDestino = telefoneDestino;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
