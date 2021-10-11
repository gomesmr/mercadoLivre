package com.zup.mercado.produto;

import com.zup.mercado.categoria.Categoria;
import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.config.validator.ExistsId;
import com.zup.mercado.config.validator.UniqueValue;
import com.zup.mercado.caracteristica.ProdutoCaracteristicaRequest;
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
    private List<ProdutoCaracteristicaRequest> caracteristicas = new ArrayList<>();
    private LocalDateTime instanteCadastro = LocalDateTime.now();

    public Produto toModel(EntityManager manager, Usuario proprietario) {
        Categoria categoria = manager.find(Categoria.class, idCategoria);
        Assert.notNull(categoria, "O id da categoria é inválido");

        return new Produto(null, nome, quantidade, descricao, valor,
                categoria, proprietario, caracteristicas, instanteCadastro);
    }

    public List<ProdutoCaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();
        for (ProdutoCaracteristicaRequest caracteristica : caracteristicas){
            if (!nomesIguais.add(caracteristica.getNome())){
                resultados.add(caracteristica.getNome());
            }
        }
        return resultados;

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

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setCaracteristicas(List<ProdutoCaracteristicaRequest> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public void setInstanteCadastro(LocalDateTime instanteCadastro) {
        this.instanteCadastro = instanteCadastro;
    }
}
