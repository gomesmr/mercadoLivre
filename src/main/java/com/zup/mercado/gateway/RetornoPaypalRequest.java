package com.zup.mercado.gateway;

import com.zup.mercado.compra.Compra;
import com.zup.mercado.transacao.StatusTransacao;
import com.zup.mercado.transacao.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RetornoPaypalRequest implements RetornoGatewayPagamento{

    @Min(0)
    @Max(1)
    private int status;
    @NotBlank
    private String idTransacao;

    public RetornoPaypalRequest(int status, String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public Transacao toModel(Compra compra) {
        return new Transacao(statusCalculado(), idTransacao, compra);
    }

    private StatusTransacao statusCalculado() {
        return this.status == 0 ? StatusTransacao.erro:StatusTransacao.sucesso;
    }

    public int getStatus() {
        return status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }
}
