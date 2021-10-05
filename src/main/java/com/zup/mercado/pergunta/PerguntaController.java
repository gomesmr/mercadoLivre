package com.zup.mercado.pergunta;

import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.config.security.usuarios.UsuarioRepository;
import com.zup.mercado.config.validator.CustomNotFoundException;
import com.zup.mercado.produto.Produto;
import com.zup.mercado.produto.ProdutoRepository;
import com.zup.mercado.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
@ControllerAdvice
@RestController
@RequestMapping(value = "/produtos")
public class PerguntaController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerguntaRepository perguntaRepository;
    @Autowired
    private EmailSender emailSender;

    @PostMapping(value = "/{id}/pergunta")
    public ResponseEntity<PerguntaResponse> cadastraPergunta(@PathVariable("id") Long id, @RequestBody PerguntaRequest request) {
        Usuario proprietario = (Usuario) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("id", "Este produto não existe."));

        Pergunta pergunta = request.toModel(produto, proprietario);
        pergunta = perguntaRepository.save(pergunta);
        emailSender.comunicaPerguntaCadastrada(pergunta);
        return  ResponseEntity.status(HttpStatus.CREATED).body(pergunta.toDto());

    }
}
