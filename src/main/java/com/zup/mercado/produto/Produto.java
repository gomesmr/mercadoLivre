package com.zup.mercado.produto;

import ch.qos.logback.core.util.COWArrayList;
import com.zup.mercado.categoria.Categoria;
import com.zup.mercado.config.security.usuarios.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private @NotBlank String nome;
    private @NotNull @Positive Integer quantidade;
    private @NotBlank @Size(min = 30, max = 1000) String descricao;
    private @NotNull @Positive BigDecimal valor;
    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario prorietario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();
    private LocalDateTime instanteCadastro;

    @Deprecated
    public Produto() {
    }

    public Produto
            (String nome, Integer quantidade, String descricao,
             BigDecimal valor, Categoria categoria, Usuario prorietario,
             List<NovaCaracteristicaRequest> caracteristicas,
             LocalDateTime instanteCadastro) {
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
        this.instanteCadastro = instanteCadastro;

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
                ", instanteCadastro=" + instanteCadastro +
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

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
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

    public void associaImagens (Set<String> links){
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto (this, link))
                .collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario possivelProprietario) {
        return this.prorietario.equals(possivelProprietario);
    }
}
