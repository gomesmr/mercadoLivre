package com.zup.mercado.produtos;

import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.config.security.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidadtor());
    }

    /**
     * Tipo de retorno do método
     * ResponseEntity<ProdutoResponse>
     * @param request
     */
    @PostMapping
    public  ResponseEntity<ProdutoResponse> cadastrar(@RequestBody @Valid ProdutoRequest request){
        Usuario proprietario = (Usuario) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Produto produto = request.toModel(manager, proprietario);
        Produto novoProduto = produtoRepository.save(produto);
        ProdutoResponse produtoResponse = new ProdutoResponse(novoProduto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponse);
    }
}
