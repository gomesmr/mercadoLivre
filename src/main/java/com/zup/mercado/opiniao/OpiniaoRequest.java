package com.zup.mercado.opiniao;

import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.produtos.Produto;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.*;

public class OpiniaoRequest {
    @Min(1)
    @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;

    public OpiniaoRequest(int nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario consumidor) {
        return new Opiniao(nota, titulo, descricao, produto, consumidor);
    }
}
