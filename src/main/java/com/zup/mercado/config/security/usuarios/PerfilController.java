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
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    PerfilRepository repo;

    @PostMapping
    public ResponseEntity<PerfilResponse> insere(@RequestBody @Valid PerfilRequest perfilRequest, UriComponentsBuilder ucb){
        Perfil perfil  = perfilRequest.toModel();
        Perfil perfilSalvo = repo.save(perfil);
        PerfilResponse perfilResponse = new PerfilResponse(perfilSalvo);
        URI uri = ucb.path("perfis/{id}").buildAndExpand(perfilResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(perfilResponse);
    }

    @PostMapping(path = "/concedeperfil")
    public ResponseEntity<PerfilResponse> concedeperfil(@RequestBody @Valid PerfilRequest perfilRequest, UriComponentsBuilder ucb){
        Perfil perfil  = perfilRequest.toModel();
        Perfil perfilSalvo = repo.save(perfil);
        PerfilResponse perfilResponse = new PerfilResponse(perfilSalvo);
        URI uri = ucb.path("perfis/{id}").buildAndExpand(perfilResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(perfilResponse);
    }
}
