package com.zup.mercado.pergunta;

import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.produto.Produto;
import com.zup.mercado.utils.FormatarData;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotNull
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario consumidor;

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(String titulo, Produto produto, Usuario consumidor) {
        this.titulo = titulo;
        this.produto = produto;
        this.consumidor = consumidor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pergunta perguntas = (Pergunta) o;
        return titulo.equals(perguntas.titulo) && produto.equals(perguntas.produto) && consumidor.equals(perguntas.consumidor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, produto, consumidor);
    }

    public String getTitulo() {
        return titulo;
    }

    public PerguntaResponse toDto() {
        return new PerguntaResponse(id, titulo, FormatarData.dataFormatada(dataHoraCriacao));
    }
}
