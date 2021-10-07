package com.zup.mercado.opiniao;

import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.produto.Produto;
import com.zup.mercado.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/produtos")
public class OpiniaoController {

//    1
    @Autowired
    private ProdutoRepository produtoRepository;
//    1
    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @PostMapping(value = "/{id}/opiniao")
    public OpiniaoResponse adicionaOpiniao(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoRequest request){
//        1
        Usuario consumidor = (Usuario) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
//        1
        Produto produto = produtoRepository.findById(id).get();
//        1
        Opiniao novaOpiniao = request.toModel(produto, consumidor);
        opiniaoRepository.save(novaOpiniao);

        return new OpiniaoResponse(novaOpiniao);

    }
}
