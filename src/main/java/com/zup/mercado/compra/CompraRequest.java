package com.zup.mercado.compra;

import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.gateway.Gateway;
import com.zup.mercado.produto.Produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;


public class CompraRequest {

    private Gateway gateway;
    @Positive
    @NotNull
    private int quantidade;
    private BigDecimal valor = new BigDecimal(0.0);

    public CompraRequest(Gateway gateway, int quantidade, BigDecimal valor) {
        this.gateway = gateway;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Compra toModel(Produto produto, Usuario comprador) {
        Compra novaCompra = new Compra(gateway, produto, quantidade, valor, comprador);
        return novaCompra;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
