package com.zup.mercado.outrossistemas;

import javax.validation.constraints.NotNull;

public class CompraRankingRequest {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public CompraRankingRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    @Override
    public String toString() {
        return "CompraRankingRequest{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }
}
