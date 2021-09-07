package com.zup.mercado.categoria;

import com.zup.mercado.config.validator.UniqueValue;
import org.aspectj.bridge.IMessage;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

public class NovaCategoriaRequest {

    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Essa categoria já existe.")
    @NotBlank
    private String nome;
    @Positive
    private Long idCategoriaMae;

    public Categoria toModel(EntityManager manager) {
        Categoria categoria = new Categoria(nome);
        if (idCategoriaMae != null){
            Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
            Assert.notNull(categoriaMae, "O id da categoria mae precisa ser válido.");
            categoria.setMae(categoriaMae);
        }
        return categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

}
