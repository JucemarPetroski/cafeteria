package com.senac.aula_api.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
public class Pedido {
    
    public Pedido() {
        // Construtor vazio para JPA
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Cliente é obrigatório")
    private String cliente;
    
    @NotBlank(message = "Status é obrigatório")
    private String status;
    
    @ManyToMany
    @NotEmpty(message = "Pelo menos um produto deve ser selecionado")
    private List<Produto> produtos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }
}
