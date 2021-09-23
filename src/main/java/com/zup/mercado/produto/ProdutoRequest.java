package com.zup.mercado.produto;

import com.zup.mercado.categoria.Categoria;
import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.config.validator.ExistsId;
import com.zup.mercado.config.validator.UniqueValue;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoRequest {
    @UniqueValue(domainClass = Produto.class, fieldName = "nome", message = "O nome do produto não pode se repetido")
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
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;
    @Size(min = 3)
    @Valid
    private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();
    private LocalDateTime instanteCadastro = LocalDateTime.now();

    public ProdutoRequest(String nome, Integer quantidade, String descricao,
                          BigDecimal valor, Long idCategoria,
                          List<NovaCaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    @Override
    public String toString() {
        return "ProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", idCategoria=" + idCategoria +
                ", caracteristicas=" + caracteristicas +
                ", instanteCadastro=" + instanteCadastro +
                '}';
    }

    public Produto toModel(EntityManager manager, Usuario proprietario) {
        Categoria categoria = manager.find(Categoria.class, idCategoria);
        Assert.notNull(categoria, "O id da categoria é inválido");

        return new Produto(nome, quantidade, descricao, valor,
                categoria, proprietario, caracteristicas, instanteCadastro);
    }

    public List<NovaCaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();
        for (NovaCaracteristicaRequest caracteristica : caracteristicas){
            if (!nomesIguais.add(caracteristica.getNome())){
                resultados.add(caracteristica.getNome());
            }
        }
        return resultados;

    }
}
