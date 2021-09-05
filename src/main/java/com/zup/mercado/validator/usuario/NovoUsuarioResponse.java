package com.zup.mercado.validator.usuario;


import com.zup.mercado.validator.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class NovoUsuarioResponse {
    private Long id;
    private String email;
    private LocalDateTime dataHoraCadastro;

    public NovoUsuarioResponse(Long id, String email, String senha, LocalDateTime dataHoraCadastro) {
        this.id = id;
        this.email = email;
        this.dataHoraCadastro = LocalDateTime.now();
    }

    public NovoUsuarioResponse(Usuario cliente) {
        this.id = cliente.getId();
        this.email = cliente.getEmail();
        this.dataHoraCadastro = cliente.getDataHoraCadastro();


    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDateTime getDataHoraCadastro() {
        return dataHoraCadastro;
    }
    public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }
}


