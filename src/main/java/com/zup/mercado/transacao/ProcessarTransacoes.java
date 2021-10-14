package com.zup.mercado.transacao;

import com.zup.mercado.compra.Compra;
import com.zup.mercado.compra.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProcessarTransacoes {

    @Autowired
    private Set<EventoTransacao> eventosTransacao;
    @Autowired
    private CompraRepository compraRepository;

    public void processaTransacao(Compra compra){
        compraRepository.save(compra);
        if (compra.processadaComSucesso()) {
            eventosTransacao.forEach(evento -> evento.processaTransacaoAprovada(compra));
        } else {
            eventosTransacao.forEach(evento -> evento.processaTransacaoRecusada(compra));
        }
    }

}
