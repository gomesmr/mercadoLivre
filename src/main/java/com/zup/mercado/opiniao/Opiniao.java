package com.zup.mercado.opiniao;

import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.produtos.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
@Entity
public class Opiniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(1) @Max(5) int nota;
    @NotBlank String titulo;
    @NotBlank @Size(max = 500) String descricao;
    @ManyToOne
    @NotNull @Valid Produto produto;
    @ManyToOne
    @NotNull @Valid Usuario consumidor;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@Min(1) @Max(5) int nota,
                   @NotBlank String titulo,
                   @NotBlank @Size(max = 500) String descricao,
                   @NotNull @Valid Produto produto,
                   @NotNull @Valid Usuario consumidor) {
                this.nota = nota;
                this.titulo = titulo;
                this.descricao = descricao;
                this.produto = produto;
                this.consumidor = consumidor;
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "id=" + id +
                ", nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", produto=" + produto +
                ", consumidor=" + consumidor +
                '}';
    }
}
