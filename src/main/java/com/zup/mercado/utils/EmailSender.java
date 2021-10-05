package com.zup.mercado.utils;

import com.zup.mercado.pergunta.Pergunta;

public interface EmailSender {
    void comunicaPerguntaCadastrada(Pergunta pergunta);
}
