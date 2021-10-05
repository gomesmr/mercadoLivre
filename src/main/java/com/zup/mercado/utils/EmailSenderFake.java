package com.zup.mercado.utils;

import com.zup.mercado.pergunta.Pergunta;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderFake implements EmailSender {
    public void comunicaPerguntaCadastrada(Pergunta pergunta) {

        System.out.printf("Email enviado: %s\n", pergunta);
    }

}
