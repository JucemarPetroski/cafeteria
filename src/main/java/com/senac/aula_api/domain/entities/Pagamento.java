package com.senac.aula_api.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Pagamento {
    
    public Pagamento() {
        // Construtor vazio para JPA
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "ID do pedido é obrigatório")
    private Long pedidoId;
    
    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private Double valor;
    
    @NotBlank(message = "Método de pagamento é obrigatório")
    private String metodo;
    
    @NotBlank(message = "Status é obrigatório")
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
