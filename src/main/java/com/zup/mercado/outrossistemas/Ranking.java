package com.zup.mercado.outrossistemas;

import com.zup.mercado.compra.Compra;
import com.zup.mercado.transacao.EventoTransacao;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements EventoTransacao {

    @Override
    public void processaTransacaoAprovada(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(),
                "idVendedor", compra.getProduto().getVendedor().getId());

        restTemplate.postForEntity("http://localhost:8080/ranking",
                request, String.class);
    }

    @Override
    public void processaTransacaoRecusada(Compra compra) {

    }
}
