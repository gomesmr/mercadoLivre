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

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
}