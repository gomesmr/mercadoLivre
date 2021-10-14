package com.zup.mercado.compra;

import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.gateway.Gateway;
import com.zup.mercado.gateway.RetornoGatewayPagamento;
import com.zup.mercado.produto.Produto;
import com.zup.mercado.produto.ProdutoRepository;
import com.zup.mercado.transacao.StatusTransacao;
import com.zup.mercado.transacao.Transacao;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identificador =  UUID.randomUUID().toString();
    @Enumerated(EnumType.STRING)
    private Gateway gateway;
    @ManyToOne
    private Produto produto;
    @Positive
    @NotNull
    private int quantidade;
    private BigDecimal valor = new BigDecimal(0.0);
    @ManyToOne
    private Usuario comprador;
    @Enumerated(EnumType.STRING)
    private StatusCompra status = StatusCompra.INICIADA;
    private LocalDateTime instanteCompra = LocalDateTime.now();
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {}

    public Compra(Gateway gateway, Produto produto,
                  int quantidade, BigDecimal valor, Usuario comprador) {
        this.gateway = gateway;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
        this.comprador = comprador;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", identificador='" + identificador + '\'' +
                ", gateway=" + gateway +
                ", status=" + status +
                ", transacoes=" + transacoes +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public String getIdentificador() {
        return this.identificador;
    }

    public Gateway getGateway() {
        return this.gateway;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public Usuario getComprador() {
        return this.comprador;
    }

    public void controleEstoque(Produto produto, ProdutoRepository produtoRepository) {
        int estoqueFinal = produto.getEstoqueProduto() - getQuantidade();
        int resposta = produtoRepository.setEstoqueProdutoById(estoqueFinal, produto.getId());
    }


//Alberto code
    public void adicionaTransacao(RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toModel(this);
        //não é permitido transação com succeso com o mesmo idTransacao
        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe uma transação igual a essa processada "+novaTransacao.toString());

        /**
         * Busco dentre as transações concluidas, se já existe uma finalizada com sucesso
         */
        Set<Transacao> transacoesConcluidasComSucesso = transacoesConcluidasComSucesso();

        /**
         * Se esta lista não estiver vazia, interrompo o processamento
         */
        Assert.isTrue(transacoesConcluidasComSucesso.isEmpty(), "Essa compra já foi concluida com sucesso!");

        /**
         * Se a lista estiver vazia, eu posso adicionar uma transação (com sucesso ou não...)
         */
        this.transacoes.add(novaTransacao);

        if (novaTransacao.getStatus().equals(StatusTransacao.sucesso)){
            this.status=StatusCompra.FINALIZADA;
        }
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> listaDeTransacoes = this.transacoes.stream()
                .filter(Transacao :: concluidaComSucesso)
                .collect(Collectors.toSet());
        Assert.isTrue(listaDeTransacoes.size()<=1, "Existe mais que uma transação concluída com sucesso nesta compra "+this.identificador);

        return listaDeTransacoes;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }

}
