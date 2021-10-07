package com.zup.mercado.opiniao;

import javax.validation.constraints.*;

public class OpiniaoResponse {
    private int nota;
    private String titulo;
    private  String descricao;

    public OpiniaoResponse(Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
