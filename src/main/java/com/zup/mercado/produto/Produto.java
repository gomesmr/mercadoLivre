package com.zup.mercado.produto;

import com.zup.mercado.categoria.Categoria;
import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.opiniao.Opiniao;
import com.zup.mercado.pergunta.Pergunta;
import com.zup.mercado.caracteristica.ProdutoCaracteristica;
import com.zup.mercado.imagem.ImagemProduto;
import com.zup.mercado.caracteristica.ProdutoCaracteristicaRequest;
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
    /**  CATEGORIA   **/
    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
    /**  PROPRIETÁRIO   **/
    @ManyToOne
    @JoinColumn(name = "idProprietario")
    private Usuario prorietario;
    /**  CARACTERÍSTICAS   **/
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<ProdutoCaracteristica> caracteristicas = new HashSet<ProdutoCaracteristica>();
    /**  IMAGENS   **/
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<ImagemProduto> imagens;
    /**  PERGUNTAS   **/
    @OneToMany(mappedBy = "produto")
    private List<Pergunta> perguntas;
    /**  OPINIÕES   **/
    @OneToMany(mappedBy = "produto")
    private List<Opiniao> opinioes;

    private LocalDateTime instanteCadastro;

    @Deprecated
    public Produto() {
    }

    public Produto
            (String nome, Integer quantidade, String descricao,
             BigDecimal valor, Categoria categoria, Usuario prorietario,
             List<ProdutoCaracteristicaRequest> caracteristicas,
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

    public Produto(Optional<Produto> produto) {
        this.nome = produto.get().getNome();
        this.quantidade = produto.get().getQuantidade();
        this.descricao = produto.get().getDescricao();
        this.valor = produto.get().getValor();
        this.categoria = produto.get().getCategoria();
        this.prorietario = produto.get().getProrietario();
        this.caracteristicas = produto.get().getCaracteristicas();
        this.instanteCadastro = produto.get().getInstanteCadastro();
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

    public LocalDateTime getInstanteCadastro() {

        return instanteCadastro;
    }

    public Set<ProdutoCaracteristica> getCaracteristicas() {

        return caracteristicas;
    }

    public List<ImagemProduto> getImagens() {
        return imagens;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
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

    public Double calcularMediaAritmetica() {
        return null;
    }
}
