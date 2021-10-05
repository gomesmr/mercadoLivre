package com.zup.mercado.pergunta;

public class PerguntaResponse {

    private Long id;
    private String titulo;
    private String dataHoraCriacao;

    public PerguntaResponse(Long id, String titulo, String dataHoraCriacao) {
        this.id = id;
        this.titulo = titulo;
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDataHoraCriacao() {
        return dataHoraCriacao;
    }
}
