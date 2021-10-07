package com.zup.mercado.produto.detalhes;

public class DetalhesProdutoOpiniao {
    private String titulo;
    private String descricao;

    public DetalhesProdutoOpiniao(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
