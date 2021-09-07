package com.zup.mercado.categoria;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private @NotBlank String nome;

    @ManyToOne
    @JoinColumn(name = "categoriaMae")
    private Categoria categoriaMae;

    @Deprecated
    public Categoria() {
    }

    public Categoria(@NotBlank String nome) {
        this.nome = nome;
    }

    public void setMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

    public Long getId() {
        return id;
    }

    public String getCategoria() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

}
