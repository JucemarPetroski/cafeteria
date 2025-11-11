package com.senac.aula_api.domain.entities;

import com.senac.aula_api.application.dto.LoginRequestDto;
import com.senac.aula_api.application.dto.usuario.UsuarioCriarRequestDto;
import com.senac.aula_api.domain.valueobjects.CPF;
import com.senac.aula_api.domain.valueobjects.EnumStatusUsuario;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public class Usuario {

    public Usuario(){}

    public Usuario(UsuarioCriarRequestDto usuario){

         this.email = usuario.email();
         this.nome = usuario.nome();
         this.cpf = new CPF(usuario.cpf());
         this.senha = usuario.senha();
         }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String senha;

    @Embedded
    private CPF cpf;

    // Relacionamento removido - será implementado quando necessário
    // @OneToMany
    // @JoinColumn(name = "usuario_id",nullable = false)
    // private List<Menu> menu;



    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    private String telefone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    private EnumStatusUsuario status = EnumStatusUsuario.ATIVO;
    private boolean acessoIrrestrito = false;

    // Papel do usuário para autorização (ex: ROLE_USER, ROLE_ADMIN)
    private String role = "ROLE_USER";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public CPF getCpf() {
        return cpf;
    }

    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }

    public EnumStatusUsuario getStatus() {
        return status;
    }

    public void setStatus(EnumStatusUsuario status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String apresentar(){
        return "Dados " + this.nome +" CPF " + this.cpf.toString();
    }
}
