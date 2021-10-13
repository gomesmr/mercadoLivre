package com.zup.mercado.compra;

import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.config.validator.CustomBusinessRuleViolation;
import com.zup.mercado.gateway.Gateway;
import com.zup.mercado.produto.Produto;
import com.zup.mercado.produto.ProdutoRepository;
import com.zup.mercado.transacao.Transacao;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", comprador=" + comprador +
                ", status=" + status +
                ", instanteCompra=" + instanteCompra +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public StatusCompra getStatus() {
        return status;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getInstanteCompra() {
        return instanteCompra;
    }

    public void controleEstoque(Produto produto, ProdutoRepository produtoRepository) {
        int estoqueFinal = produto.getEstoqueProduto() - getQuantidade();
        int resposta = produtoRepository.setEstoqueProdutoById(estoqueFinal, produto.getId());
    }

    public void adicionaTransacao(Transacao transacao) {

        //se existe uma transacao aprovada: cai fora

        boolean verificaTransacoesAprovadas = transacoesAprovadas().size() > 2 ;
        boolean transacaoAprovada = transacao.aprovada();

        if (verificaTransacoesAprovadas && transacaoAprovada) {
            throw new CustomBusinessRuleViolation("transacoes", "Esta compra já foi finalizada!");
        }
        this.transacoes.add(transacao);
    }

    public boolean aprovada() {
        return !transacoesAprovadas().isEmpty();
    }

    public Set<Transacao> transacoesAprovadas() {
        return this.transacoes.stream()
                .filter(Transacao::aprovada)
                .collect(Collectors.toSet());
    }

}
