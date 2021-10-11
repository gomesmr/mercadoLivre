package com.zup.mercado.gateway;

public enum Gateway {
    PAGSEGURO(new GatewayPagSeguro()),
    PAYPAL(new GatewayPaypal());

    private GatewayPagamento gateway;

    Gateway(GatewayPagamento gateway) {
        this.gateway = gateway;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gateway;
    }
}

