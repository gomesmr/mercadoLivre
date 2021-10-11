package com.zup.mercado.gateway;

import com.zup.mercado.compra.Compra;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class GatewayPaypal implements GatewayPagamento {
    @Override
    public String gerarPagamento(Compra compra) {
        String uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}/paypal")
                .buildAndExpand(compra.getIdentificador())
                .toUriString();
        return String.format("paypal.com?buyerId=%s&redirectUrl=%s", compra.getIdentificador(), uri);
    }
}
