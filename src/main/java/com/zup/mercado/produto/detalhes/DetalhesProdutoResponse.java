package com.zup.mercado.produto.detalhes;

import com.zup.mercado.categoria.Categoria;
import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.imagem.ImagemProduto;
import com.zup.mercado.produto.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetalhesProdutoResponse {
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String categoria;
    private String prorietario;
    private List<DetalhesProdutoCaracteristica> caracteristicas;
    private List<DetalhesProdutoPergunta> perguntas;
    private List<DetalhesProdutoImagem> imagens;
    private List<DetalhesProdutoOpiniao> opinioes;
    private Double mediaNota;
    private LocalDateTime instanteCadastro;

    public DetalhesProdutoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getValor();
        Categoria cat = produto.getCategoria();
        this.categoria = cat.getCategoria();

        this.prorietario = produto.getProrietario().getEmail();

        this.perguntas = produto.getPerguntas()
                .stream()
                .map(DetalhesProdutoPergunta::new)
                .collect(Collectors.toList());

        this.opinioes = produto.getOpinioes()
                .stream()
                .map(DetalhesProdutoOpiniao::new)
                .collect(Collectors.toList());

        this.imagens = produto.getImagens()
                .stream()
                .map(DetalhesProdutoImagem::new)
                .collect(Collectors.toList());

        this.caracteristicas = produto.getCaracteristicas()
                .stream()
                .map(DetalhesProdutoCaracteristica::new)
                .collect(Collectors.toList());

        this.mediaNota = produto.calcularMediaAritmetica();
        //this.total = produto.todasNotas().size();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProrietario() {
        return prorietario;
    }

    public List<DetalhesProdutoCaracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public List<DetalhesProdutoPergunta> getPerguntas() {
        return perguntas;
    }

    public List<DetalhesProdutoImagem> getImagens() {
        return imagens;
    }

    public Double getMediaNota() {
        return mediaNota;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }
}
