package com.zup.mercado.gateway;

import com.zup.mercado.compra.Compra;
import com.zup.mercado.transacao.Transacao;

public interface RetornoGatewayPagamento {
    /**
     *
     * @param compra
     * @return uma nova transação em função do Gateway de Pagamento
     */
    Transacao toModel(Compra compra);
}
