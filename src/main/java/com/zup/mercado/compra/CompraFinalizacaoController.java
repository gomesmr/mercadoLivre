package com.zup.mercado.compra;

import com.zup.mercado.config.validator.CustomNotFoundException;
import com.zup.mercado.gateway.RetornoPagseguroRequest;
import com.zup.mercado.transacao.ProcessarTransacoes;
import com.zup.mercado.transacao.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class CompraFinalizacaoController {

    @Autowired
    CompraRepository compraRepository;
    @Autowired
    ProcessarTransacoes processarTransacoes;


    @PostMapping(value = "/{id}/pagseguro")
    public void finalizaCompraPagSeguro(@PathVariable("id") String identificador, @RequestBody @Valid RetornoPagseguroRequest request){
        Compra compra = compraRepository.findByIdentificador(identificador).orElseThrow(() -> {
            throw new CustomNotFoundException("identificador", "Não existe uma compra com este identificador");
        });

        Transacao transacao = request.toModel(compra);
        compra.adicionaTransacao(transacao);
        processarTransacoes.processaTransacao(compra);
    }

}
