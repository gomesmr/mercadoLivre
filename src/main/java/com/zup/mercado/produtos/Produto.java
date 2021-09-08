package com.zup.mercado.produtos;

import com.zup.mercado.categoria.Categoria;
import com.zup.mercado.config.security.usuarios.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String nome;
    private @NotNull @Positive Integer quantidade;
    private @NotBlank String descricao;
    private @NotNull @Positive BigDecimal valor;
    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario prorietario;


    @Deprecated
    public Produto() {
    }

    public Produto
            (String nome, Integer quantidade, String descricao, BigDecimal valor, Categoria categoria, Usuario prorietario) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.prorietario = prorietario;
    }

    public Long getId() {
        return id;
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

    public Usuario getProrietario() {
        return prorietario;
    }
}
