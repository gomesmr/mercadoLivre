package com.zup.mercado.produtos;

import com.zup.mercado.categoria.Categoria;
import com.zup.mercado.config.security.usuarios.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProdutoRequest {
    @NotBlank
    private String nome;
    @Positive
    private Integer quantidade;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    private Long idCategoria;

    public ProdutoRequest(String nome, Integer quantidade, String descricao, BigDecimal valor, Long idCategoria) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.idCategoria = idCategoria;
    }

    public Produto toModel(EntityManager manager, Usuario proprietario) {
        System.out.println("ID da Categoria" + idCategoria);
        Categoria categoria = manager.find(Categoria.class, idCategoria);
        Assert.notNull(categoria, "O id da categoria é inválido");
        return new Produto(nome, quantidade, descricao, valor, categoria, proprietario);
    }
}
