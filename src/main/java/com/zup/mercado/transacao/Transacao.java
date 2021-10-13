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
    @Enumerated(EnumType.STRING)
    private StatusTransacao status;
    @NotBlank
    private String idTransacaoGateway;
    @NotNull
    @Valid
    @ManyToOne
    private Compra compra;
    private LocalDateTime instanteProcessamento = LocalDateTime.now();

    @Deprecated
    public Transacao() {}

    public Transacao(StatusTransacao status, String idTransacaoGateway, Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.compra = compra;
    }


    public StatusTransacao getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return idTransacaoGateway.equals(transacao.idTransacaoGateway);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacaoGateway);
    }

    public boolean concluidaComSucesso() {
        return Objects.equals(this.status, StatusTransacao.sucesso);
    }
}
