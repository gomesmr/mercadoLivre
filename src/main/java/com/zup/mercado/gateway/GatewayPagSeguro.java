package com.zup.mercado.gateway;

import com.zup.mercado.compra.Compra;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class GatewayPagSeguro implements GatewayPagamento {
    @Override
    public String gerarPagamento(Compra compra) {
        String uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}/pagseguro")
                .buildAndExpand(compra.getIdentificador())
                .toUriString();
        return String.format("pagseguro.com?returnId=%s&redirectUrl=%s", compra.getIdentificador(), uri);
    }
}
