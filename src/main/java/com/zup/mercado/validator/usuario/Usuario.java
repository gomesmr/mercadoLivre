package com.zup.mercado.validator.usuario;

import com.sun.istack.NotNull;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private @NotBlank @Email String email;
    private @NotBlank @Size(min = 6) String senha;
    private @NotNull LocalDateTime dataHoraCadastro;

    @Deprecated
    public Usuario() {}

    public Usuario(String email, String senha, LocalDateTime dataHoraCadastro) {
        Assert.hasLength(email, "O e-mail é obrigatório");
        Assert.hasLength(senha, "A senha é obrigatória");

        this.email = email;
        this.senha = senha;
        this.dataHoraCadastro = dataHoraCadastro;
    }

    @Override
    public String toString(){
        return "Cliente [email=" + email + " dataHora= " + dataHoraCadastro + "]";
    }

    public Long getId() {return id;}
    public String getEmail() {return email;}
    public LocalDateTime getDataHoraCadastro() {return dataHoraCadastro;}
}
