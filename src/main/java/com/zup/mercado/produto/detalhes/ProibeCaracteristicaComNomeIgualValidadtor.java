package com.zup.mercado.produto.detalhes;

import com.sun.xml.bind.v2.TODO;
import com.zup.mercado.produto.ProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProibeCaracteristicaComNomeIgualValidadtor implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoRequest.class.isAssignableFrom(clazz);
    }

    /**
     * {@link TODO} Fazer testes aqui
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }
        ProdutoRequest request = (ProdutoRequest) target;
        Set<String> nomesIguais = request.buscaCaracteristicasIguais();
        if (!nomesIguais.isEmpty()){
            errors.rejectValue("caracteristicas", null, "Você está cadastrando categorias iguais "+nomesIguais);
        }

    }
}
