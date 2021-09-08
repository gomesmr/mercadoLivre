package com.zup.mercado.produtos;

import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.config.security.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidadtor());
    }

    /**
     * Tipo de retorno do método
     * ResponseEntity<ProdutosResponse>
     * @param request
     */
    @PostMapping
    public  ResponseEntity<ProdutosResponse> cadastrar(@RequestBody @Valid ProdutoRequest request){
        Usuario proprietario = usuarioRepository.findByEmail("gomes.mr@gmail.com").get();
        Produto produto = request.toModel(manager, proprietario);
        //return produto.toString();
        Produto novoProduto = produtoRepository.save(produto);
        ProdutosResponse produtosResponse = new ProdutosResponse(novoProduto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtosResponse);
    }
}
