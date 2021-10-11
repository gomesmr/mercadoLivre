package com.zup.mercado.utils;

import com.zup.mercado.compra.Compra;
import com.zup.mercado.pergunta.Pergunta;

public interface EmailSender {
    void comunicaPerguntaCadastrada(Pergunta pergunta);

    void comunicaCompraRealizada(Compra compra);
}
