package com.zup.mercado.outrossistemas;

import javax.validation.constraints.NotNull;

public class CompraNfRequest {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public CompraNfRequest(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    @Override
    public String toString() {
        return "CompraNfRequest{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }
}
