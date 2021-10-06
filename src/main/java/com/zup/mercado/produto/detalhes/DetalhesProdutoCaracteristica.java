package com.zup.mercado.produto.detalhes;

import com.zup.mercado.caracteristica.ProdutoCaracteristica;

public class DetalhesProdutoCaracteristica {
    private String nome;

    private String descricao;

    public DetalhesProdutoCaracteristica(ProdutoCaracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
}
