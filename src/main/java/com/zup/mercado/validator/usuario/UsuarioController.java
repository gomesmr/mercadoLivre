package com.zup.mercado.validator.usuario;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/clientes")
public class UsuarioController {
    private final UsuarioRepository clienteRepository;

    public UsuarioController(UsuarioRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NovoUsuarioResponse cadastroCliente(@RequestBody @Valid NovoUsuarioRequest novoCliente)
    throws Exception{
        Usuario cliente = novoCliente.toModel();
        return new NovoUsuarioResponse(clienteRepository.save(cliente));

     }
}
