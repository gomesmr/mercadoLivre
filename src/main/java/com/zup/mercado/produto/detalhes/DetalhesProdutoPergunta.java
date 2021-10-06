package com.zup.mercado.produto.detalhes;

import com.zup.mercado.pergunta.Pergunta;

public class DetalhesProdutoPergunta {
    private String descricao;

    public DetalhesProdutoPergunta(Pergunta pergunta) {
        this.descricao = pergunta.getTitulo();
    }

    public String getDescricao() {
        return descricao;
    }
}
