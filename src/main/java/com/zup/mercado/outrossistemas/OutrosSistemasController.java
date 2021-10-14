package com.zup.mercado.outrossistemas;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutrosSistemasController {

    @PostMapping(value = "/notas-fiscais")
    public void criaNota(@RequestBody CompraNfRequest request) throws InterruptedException{
        System.out.println("Criando nota para "+ request.toString() );
        Thread.sleep(1500);
    }

    @PostMapping(value = "/ranking")
    public void ranking(@RequestBody CompraRankingRequest request) throws InterruptedException{
        System.out.println("Criando rank para "+ request.toString());
        Thread.sleep(1500);
    }

}
