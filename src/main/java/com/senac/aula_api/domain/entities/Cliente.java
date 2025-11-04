
package com.senac.aula_api.domain.entities;

import com.senac.aula_api.domain.valueobjects.CPF;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends Usuario {



    public Cliente(String nome, CPF cpf, String email, BigDecimal valorConsumido) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setEmail(email);
        this.setValorConsumido(valorConsumido);

    }

    private BigDecimal valorConsumido;

    public BigDecimal getValorConsumido() {
        return valorConsumido;
    }

    public void setValorConsumido(BigDecimal valorConsumido) {
        this.valorConsumido = valorConsumido;
    }

    @Override
    public String apresentar() {
        String resposta = "";
        resposta += super.apresentar() + "Valor Consumido" + this.valorConsumido.toString();
        return resposta;


    }
}