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
    private @NotNull @Positive Integer estoqueProduto;
    private @NotBlank @Size(min = 30, max = 1000) String descricao;
    private @NotNull @Positive BigDecimal valor;
    private LocalDateTime instanteCadastro;
    /**  CATEGORIA   **/
    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
    /**  PROPRIETÁRIO   **/
    @ManyToOne
    @JoinColumn(name = "idVendedor")
    private Usuario vendedor;
    /**  CARACTERÍSTICAS   **/
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<ProdutoCaracteristica> caracteristicas = new HashSet<>();
    /**  IMAGENS   **/
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<ImagemProduto> imagens;
    /**  PERGUNTAS   **/
    @OneToMany(mappedBy = "produto")
    private List<Pergunta> perguntas;
    /**  OPINIÕES   **/
    @OneToMany(mappedBy = "produto")
    private List<Opiniao> opinioes;

    @Deprecated
    public Produto() {}

    public Produto
            (Long id, String nome, Integer estoqueProduto, String descricao,
             BigDecimal valor, Categoria categoria, Usuario vendedor,
             List<ProdutoCaracteristicaRequest> caracteristicas,
             LocalDateTime instanteCadastro) {
        this.id = id;
        this.nome = nome;
        this.estoqueProduto = estoqueProduto;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.vendedor = vendedor;
        this.caracteristicas.addAll(caracteristicas
                .stream().map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet()));
        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no mínimo 3 ou mais características.");
        this.instanteCadastro = instanteCadastro;

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getEstoqueProduto() {
        return estoqueProduto;
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

    public Usuario getVendedor() {
        return vendedor;
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

    public List<Opiniao> getOpinioes() {
        return opinioes;
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
        return this.vendedor.equals(possivelProprietario);
    }

    public double calcularMediaAritmetica() {
        List<Double> notas = todasNotas();
        Double totalNotas = notas.stream().reduce(0.0, Double::sum);
        Integer quantidadeNotas = notas.size();
        if (totalNotas == 0 || quantidadeNotas == 0) {
            return 0;
        }
        return  totalNotas/quantidadeNotas;
    }

    /**
     * Obter a lista de todas as notas
     */
    public List<Double> todasNotas() {
        return opinioes.stream()
                .map(Opiniao::getNota)
                .map(nota -> (double) nota)
                .collect(Collectors.toList());
    }


}
