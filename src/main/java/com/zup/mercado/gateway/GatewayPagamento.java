package com.zup.mercado.gateway;

import com.zup.mercado.compra.Compra;

public interface GatewayPagamento {
        String gerarPagamento(Compra compra);
}

