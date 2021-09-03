package com.zup.mercado.cliente;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NovoClienteResponse cadastroCliente(@RequestBody @Valid NovoClienteRequest novoCliente)
    throws Exception{
        Cliente cliente = novoCliente.toModel();
        return new NovoClienteResponse(clienteRepository.save(cliente));

     }
}
