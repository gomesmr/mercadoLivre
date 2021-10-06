package com.zup.mercado.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PersistenceContext
    EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaResponse> cadastrar(@RequestBody @Valid CategoriaRequest request){
        Categoria categoria = request.toModel(manager);
        Categoria novaCategoria = categoriaRepository.save(categoria);
        CategoriaResponse categoriaResponse = new CategoriaResponse(novaCategoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaResponse);
    }


}
