package com.zup.mercado.config.security.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository repo;

    @PostMapping
    public ResponseEntity<UsuarioResponse> insere(@RequestBody @Valid UsuarioRequest usuarioRequest, UriComponentsBuilder ucb){
            Usuario usuario  = usuarioRequest.toModel();
            Usuario usuarioSalvo = repo.save(usuario);
            UsuarioResponse usuarioResponse = new UsuarioResponse(usuarioSalvo);
            URI uri = ucb.path("usuarios/{id}").buildAndExpand(usuarioResponse.getId()).toUri();
            return ResponseEntity.created(uri).body(usuarioResponse);
    }
}
