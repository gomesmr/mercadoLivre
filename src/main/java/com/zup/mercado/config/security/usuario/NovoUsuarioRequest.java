package com.zup.mercado.config.security.usuario;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class NovoUsuarioRequest {
    private @NotBlank @Email String email;
    private @Size(min = 6, max = 16) @NotNull @NotBlank String senha;
    private @NotNull LocalDateTime dataHoraCadastro;

    public NovoUsuarioRequest(String email, String senha, LocalDateTime dataHoraCadastro) {
        this.email = email;
        this.senha = senha;
        this.dataHoraCadastro = LocalDateTime.now();
    }

    public Usuario toModel(){
        return new Usuario(this.email, this.senha, this.dataHoraCadastro);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }
}


