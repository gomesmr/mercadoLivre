package com.zup.mercado.compra;

import com.zup.mercado.config.validator.CustomNotFoundException;
import com.zup.mercado.gateway.RetornoGatewayPagamento;
import com.zup.mercado.gateway.RetornoPagseguroRequest;
import com.zup.mercado.gateway.RetornoPaypalRequest;
import com.zup.mercado.transacao.ProcessarTransacoes;
import org.springframework.beans.factory.annotation.Autowired;
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
        return processa(identificador, request);

    }
    @PostMapping(value = "/{id}/paypal")
    public String finalizaCompraPaypal(@PathVariable("id") String identificador, @RequestBody @Valid RetornoPaypalRequest request){
        System.out.println("STOP");
        return processa(identificador, request);

    }

    private String processa(String identificador, RetornoGatewayPagamento retornoGatewayPagamento) {
        Compra compra = compraRepository.findByIdentificador(identificador).orElseThrow(() -> {
            throw new CustomNotFoundException("identificador", "Não existe uma compra com este identificador");
        });
        compra.adicionaTransacao(retornoGatewayPagamento);
        compraRepository.save(compra);
        return compra.toString();
    }


}
