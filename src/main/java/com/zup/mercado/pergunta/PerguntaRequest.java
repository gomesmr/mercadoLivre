package com.zup.mercado.pergunta;

import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.produto.Produto;

import javax.validation.constraints.NotBlank;


public class PerguntaRequest {

    @NotBlank
    private String titulo;


    public Pergunta toModel(Produto produto, Usuario usuario) {

        return new Pergunta(titulo, produto, usuario);
    }

    public String getTitulo() {
        return titulo;
    }
}
