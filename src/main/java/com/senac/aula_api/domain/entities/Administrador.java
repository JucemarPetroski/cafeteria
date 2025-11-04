package com.senac.aula_api.domain.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Administrador extends Usuario {
    private boolean acessoIrrestrito;

    public boolean isAcessoIrrestrito() {
        return acessoIrrestrito;
    }

    public void setAcessoIrrestrito(boolean acessoIrrestrito) {
        this.acessoIrrestrito = acessoIrrestrito;
    }

    @Override
    public String apresentar() {
        return "Eu sou o Administrador e tenho acesso" + (this.acessoIrrestrito ? "Irrestrito!" : "Nenhum");
    }


}
