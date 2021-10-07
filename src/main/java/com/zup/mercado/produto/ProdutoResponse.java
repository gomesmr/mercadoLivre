package com.zup.mercado.produto;

import com.zup.mercado.categoria.Categoria;
import com.zup.mercado.config.security.usuarios.Usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProdutoResponse {
    private String nome;
    private Integer quantidade;
    private String descricao;
    private BigDecimal valor;
    private Categoria categoria;
    private Usuario proprietario;
    private LocalDateTime instanteCadastro;

    public ProdutoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.quantidade = produto.getQuantidade();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.categoria = produto.getCategoria();
        this.proprietario = produto.getProrietario();
        this.instanteCadastro = produto.getInstanteCadastro();
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }
}
