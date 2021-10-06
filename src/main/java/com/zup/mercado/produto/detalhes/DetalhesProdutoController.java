package com.zup.mercado.produto.detalhes;

import com.zup.mercado.config.validator.CustomNotFoundException;
import com.zup.mercado.produto.Produto;
import com.zup.mercado.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produtos")
public class DetalhesProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping(value = "/{id}")
    public DetalhesProdutoResponse detalhesProduto(@PathVariable("id") Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() ->
                new CustomNotFoundException("id", "Produto não encontrado"));

        return new DetalhesProdutoResponse(produto);

    }
}
