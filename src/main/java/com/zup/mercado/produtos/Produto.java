package com.zup.mercado.produtos;

import com.zup.mercado.categoria.Categoria;
import com.zup.mercado.config.security.usuarios.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto
            (String nome, Integer quantidade, String descricao,
             BigDecimal valor, Categoria categoria, Usuario prorietario,
             List<NovaCaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.prorietario = prorietario;
        this.caracteristicas.addAll(caracteristicas
                .stream().map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet()));
        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no mínimo 3 ou mais características.");

    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", categoria=" + categoria +
                ", prorietario=" + prorietario +
                ", caracteristicas=" + caracteristicas +
                '}';
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
