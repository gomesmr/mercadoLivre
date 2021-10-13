package com.zup.mercado.utils;

import com.zup.mercado.compra.Compra;
import com.zup.mercado.pergunta.Pergunta;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderFake implements EmailSender {
    public void comunicaPerguntaCadastrada(Pergunta pergunta) {

        System.out.printf("Email enviado: %s\n", pergunta);
    }

    @Override
    public void comunicaCompraRealizada(Compra compra) {

        System.out.printf("Email enviado: %s\n", compra.toString());

    }

    @Override
    public void comunicaPagamentoRecusado(Compra compra) {
        System.out.printf("Email Enviado Informa Compra Recusada: %s\n", compra.toString());
    }


}
