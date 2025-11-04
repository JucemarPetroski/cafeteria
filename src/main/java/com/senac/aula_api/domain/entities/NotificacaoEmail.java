package com.senac.aula_api.domain.entities;

import com.senac.aula_api.domain.interfaces.INotificacao;

public class NotificacaoEmail implements INotificacao {

    private String emailDestino;

    public NotificacaoEmail(String emailDestino){
        this.emailDestino = emailDestino;

    }
    @Override
    public void Enviar(String mensagem) {
        System.out.println("Email para: "+this.emailDestino+" Mensagem: "+mensagem);
    }
}
