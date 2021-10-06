package com.zup.mercado.caracteristica;

import com.zup.mercado.produto.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProdutoCaracteristicaRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public ProdutoCaracteristicaRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ProdutoCaracteristicaRequest{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public ProdutoCaracteristica toModel(@NotNull @Valid Produto produto) {
        return new ProdutoCaracteristica(nome, descricao, produto);
    }
}
