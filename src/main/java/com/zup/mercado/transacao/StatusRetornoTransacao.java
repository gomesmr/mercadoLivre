package com.zup.mercado.transacao;

public enum StatusRetornoTransacao {
    SUCESSO,ERRO;

    public static StatusRetornoTransacao getStatusTransacao(Byte status) {
        if (status == 0) {
            return  ERRO;
        }
        return  SUCESSO;
    }
}
