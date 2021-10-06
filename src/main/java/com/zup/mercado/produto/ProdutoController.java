package com.zup.mercado.produto;

import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.config.security.usuarios.UsuarioRepository;
import com.zup.mercado.imagem.NovasImagensRequest;
import com.zup.mercado.caracteristica.ProibeCaracteristicaComNomeIgualValidadtor;
import com.zup.mercado.utils.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private Uploader uploaderFake;

    @InitBinder(value = "ProdutoRequest")
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

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public void adicionaImagens (@PathVariable("id") Long id, @Valid NovasImagensRequest request){
        /**
         * 1) enviar imagens para onde elas vão ficar
         * 2) pegar os links de todas as imagens
         * 3) preciso carregar o produto
         * 4) associar esses links com o produto em questão
         * 5) depois que associar eu preciso atualizar a nova versão do produto
         */
        Usuario proprietario = (Usuario) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Produto produto = produtoRepository.findById(id).get();
        if (!produto.pertenceAoUsuario(proprietario)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploaderFake.envia(request.getImagens());
        System.out.println(links);

        produto.associaImagens(links);

        manager.merge(produto);
    }


        /**
         * 1- verificar se o produto existe
         * 2- converter toModel
         * 3- Atualizo o BD
         */
/**
    @PutMapping ("/{id}")
    @Transactional
    public ResponseEntity<Object> atualizarProduto(@PathVariable("id") Long id, @RequestBody @Valid ProdutoRequest request){
        Usuario proprietario = (Usuario) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        Produto produto = produtoRepository.findById(id).orElseThrow(() ->
                new CustomNotFoundException("id", "Este produto não existe.");

        modificaProduto(Produto)
        Produto produtoRequest = request.toModel(produto);

        ProdutoResponse response = new ProdutoResponse(produtoRequest);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    **/


}


















