package com.zup.mercado.categoria;

public class CategoriaResponse {
    private Long id;
    private String categoria;
    private Categoria categoriaMae;

    public CategoriaResponse(Categoria categoria) {
        this.id = categoria.getId();
        this.categoria = categoria.getCategoria();
        this.categoriaMae = categoria.getCategoriaMae();
    }

    public Long getId() {
        return id;
    }
    public String getCategoria() {
        return categoria;
    }
    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

}