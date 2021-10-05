package com.zup.mercado.produto.detalhes;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class NovasImagensRequest {

    /**
     * Cara cterística da Bean Validation, Size min = 1,
     * deixa passar a validação se for NULO. Por essa razão
     * inclui-se o @NotNull
     */
    @NotNull
    @Size(min = 1)
    private List<MultipartFile> imagens = new ArrayList<>();

    /**
     * Quando eu sei que o construtor é necessário ou não???
     * @param imagens
     */

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
