package com.zup.mercado.opiniao;

import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.produto.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
public class Opiniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(1) @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank @Size(max = 500)
    private  String descricao;
    @ManyToOne
    @NotNull
    private Produto produto;
    @ManyToOne
    @NotNull
    private Usuario consumidor;

    @Deprecated
    public Opiniao() {}

    public Opiniao(int nota,
                   String titulo,
                   String descricao,
                   Produto produto,
                   Usuario consumidor) {
                this.nota = nota;
                this.titulo = titulo;
                this.descricao = descricao;
                this.produto = produto;
                this.consumidor = consumidor;
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
