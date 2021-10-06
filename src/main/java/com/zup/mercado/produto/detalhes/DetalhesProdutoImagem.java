package com.zup.mercado.produto.detalhes;

import com.zup.mercado.imagem.ImagemProduto;

public class DetalhesProdutoImagem {
    private String link;

    public DetalhesProdutoImagem(ImagemProduto imagem) {
        this.link = imagem.getLink();
    }

    public String getLinkImagem() {
        return link;
    }
}
