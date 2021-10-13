package com.zup.mercado.gateway;

import com.zup.mercado.compra.Compra;
import com.zup.mercado.transacao.StatusRetornoPagseguro;
import com.zup.mercado.transacao.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagseguroRequest {
    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusRetornoPagseguro status;

    public RetornoPagseguroRequest(String idTransacao, StatusRetornoPagseguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }
    public Transacao toModel(Compra compra) {
        return new Transacao(status.normalizaStatus(), idTransacao, compra);
    }

}
