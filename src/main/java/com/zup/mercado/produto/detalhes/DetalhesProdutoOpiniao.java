package com.zup.mercado.produto.detalhes;

import com.zup.mercado.opiniao.Opiniao;

public class DetalhesProdutoOpiniao {
    private String titulo;
    private String descricao;

    public DetalhesProdutoOpiniao(Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
