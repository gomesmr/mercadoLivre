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
        //verifica a lista de transações

        if (compra.aprovada()) {
            eventosTransacao.forEach(evento -> evento.processaTransacaoAprovada(compra));
            compraRepository.save(compra);
        } else {
            eventosTransacao.forEach(evento -> evento.processaTransacaoRecusada(compra));
        }
    }

}
