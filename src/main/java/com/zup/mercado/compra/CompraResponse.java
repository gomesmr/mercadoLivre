package com.zup.mercado.compra;

import com.zup.mercado.gateway.Gateway;
import com.zup.mercado.produto.Produto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CompraResponse {
    private String indentificador;
    private Gateway gateway;
    private String produto;
    private BigDecimal valorTotal = new BigDecimal(0.0);

    public CompraResponse(Compra compra) {
        this.indentificador = compra.getIdentificador();
        this.gateway = compra.getGateway();
        this.produto = compra.getProduto().getNome();
        BigDecimal multiply = new BigDecimal(compra.getQuantidade());
        this.valorTotal = compra.getValor().multiply(multiply);
    }

    public String getIndentificador() {
        return indentificador;
    }

    public void setIndentificador(String indentificador) {
        this.indentificador = indentificador;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
