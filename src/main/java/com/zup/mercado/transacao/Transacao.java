package com.zup.mercado.transacao;

import com.zup.mercado.compra.Compra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private StatusRetornoTransacao status;
    @NotBlank
    private String idTransacao;
    @NotNull
    @Valid
    @ManyToOne
    private Compra compra;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Deprecated
    public Transacao() {
    }

    public Transacao(StatusRetornoTransacao status, String idTransacao, Compra compra) {
        this.status = status;
        this.idTransacao = idTransacao;
        this.compra = compra;
    }

    public StatusRetornoTransacao getStatus() {
        return status;
    }

    public boolean aprovada() {
        return this.status.equals(StatusRetornoTransacao.SUCESSO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return idTransacao.equals(transacao.idTransacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacao);
    }
}
