package com.zup.mercado.transacao;

public enum StatusRetornoPagseguro {
    SUCESSO,ERRO;


    public StatusTransacao normalizaStatus(){
        if(this.equals(SUCESSO)){
            return StatusTransacao.sucesso;
        }
        return StatusTransacao.erro;

    }
}
