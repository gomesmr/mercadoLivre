package com.zup.mercado.config.security.usuario;


import com.zup.mercado.config.security.SenhaLimpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    @Autowired
    private SenhaLimpa senhaLimpa;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NovoUsuarioResponse cadastroCliente(@RequestBody @Valid NovoUsuarioRequest novoUsuario)
    throws Exception{
        Usuario usuario = novoUsuario.toModel();
        NovoUsuarioResponse usuarioResponse = senhaLimpa.salvarUsuario(usuario);
        return usuarioResponse;

     }
}
