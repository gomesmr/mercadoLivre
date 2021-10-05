package com.zup.mercado.produto;

import com.zup.mercado.categoria.Categoria;
import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.produto.detalhes.NovaCaracteristicaRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class ProdutoTest {
    @Test
    @DisplayName("um produto precisa de no mínimo 3 características")
            @ParameterizedTest
            @MethodSource("geradoTeste1")
    void teste1(Collection<NovaCaracteristicaRequest> caracteristicas)
        throws Exception{
        Usuario proprietario = new Usuario("gomes.mr@gmail.com", "12345");
        Categoria categoria = new Categoria("categoria");

        new Produto("nome", 10, "descricao", BigDecimal.TEN,
                categoria, proprietario, (List<NovaCaracteristicaRequest>) caracteristicas, null);
    
    }
}
