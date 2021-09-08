package com.zup.mercado.produtos;

import com.zup.mercado.categoria.Categoria;
import com.zup.mercado.config.security.usuarios.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProdutosResponse {
    private Long id;
    private String nome;
    private Integer quantidade;
    private String descricao;
    private BigDecimal valor;
    private Categoria categoria;
    private Usuario proprietario;

    public ProdutosResponse(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.quantidade = produto.getQuantidade();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.categoria = produto.getCategoria();
        this.proprietario = produto.getProrietario();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }
}
