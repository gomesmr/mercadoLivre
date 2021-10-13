package com.zup.mercado.transacao;

import com.zup.mercado.compra.Compra;

public interface EventoTransacao {
    void processaTransacaoAprovada(Compra compra);

    void processaTransacaoRecusada(Compra compra);
}
