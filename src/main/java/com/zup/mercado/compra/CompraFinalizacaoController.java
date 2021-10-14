package com.zup.mercado.compra;

import com.zup.mercado.config.validator.CustomNotFoundException;
import com.zup.mercado.gateway.RetornoGatewayPagamento;
import com.zup.mercado.gateway.RetornoPagseguroRequest;
import com.zup.mercado.gateway.RetornoPaypalRequest;
import com.zup.mercado.outrossistemas.NotaFiscal;
import com.zup.mercado.outrossistemas.Ranking;
import com.zup.mercado.transacao.ProcessarTransacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CompraFinalizacaoController {

    @Autowired
    CompraRepository compraRepository;
    @Autowired
    ProcessarTransacoes processarTransacoes;


    @PostMapping(value = "/{id}/pagseguro")
    public String finalizaCompraPagSeguro(@PathVariable("id") String identificador, @RequestBody @Valid RetornoPagseguroRequest request){
        return processa(identificador, request, "PAGSEGURO");

    }
    @PostMapping(value = "/{id}/paypal")
    public String finalizaCompraPaypal(@PathVariable("id") String identificador, @RequestBody @Valid RetornoPaypalRequest request){
        return processa(identificador, request, "PAYPALL");

    }

    private String processa(String identificador, RetornoGatewayPagamento retornoGatewayPagamento, String gateway) {
        Compra compra = compraRepository.findByIdentificador(identificador).orElseThrow(() -> {
            throw new CustomNotFoundException("identificador", "Não existe uma compra com este identificador");
        });
        Assert.isTrue(compra.getGateway().equals(gateway), "Essa compra não pode ser processada: Gateway incorreto");
        compra.adicionaTransacao(retornoGatewayPagamento);
        processarTransacoes.processaTransacao(compra);
        return compra.toString();
    }


}
