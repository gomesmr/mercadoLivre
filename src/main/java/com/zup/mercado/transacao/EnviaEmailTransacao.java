package com.zup.mercado.transacao;

import com.zup.mercado.compra.Compra;
import com.zup.mercado.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnviaEmailTransacao implements EventoTransacao{

    @Autowired
    private EmailSender emailSender;

    @Override
    public void processaTransacaoAprovada(Compra compra) {
        this.emailSender.comunicaCompraRealizada(compra);

    }

    @Override
    public void processaTransacaoRecusada(Compra compra) {
        this.emailSender.comunicaPagamentoRecusado(compra);
    }
}
