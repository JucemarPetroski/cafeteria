package com.senac.aula_api.domain.entities;

import com.senac.aula_api.domain.interfaces.INotificacao;

public class NotificacaoSms implements INotificacao {
    private String telefoneDestino;

    public NotificacaoSms(String telefoneDestino){
        this.telefoneDestino = telefoneDestino;
    }

    @Override
    public void Enviar(String mensagem) {
        System.out.println("Telefone Destino: "+this.telefoneDestino+"Mensagem: "+mensagem);
    }
}
