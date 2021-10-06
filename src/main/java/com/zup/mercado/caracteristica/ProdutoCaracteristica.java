package com.zup.mercado.caracteristica;

import com.zup.mercado.produto.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
public class ProdutoCaracteristica {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String nome;
    private String descricao;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public ProdutoCaracteristica() {
    }

    public ProdutoCaracteristica(String nome, String descricao, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "ProdutoCaracteristica{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
