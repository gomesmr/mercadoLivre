package com.zup.mercado.compra;

import com.zup.mercado.config.security.usuarios.Usuario;
import com.zup.mercado.config.security.usuarios.UsuarioRepository;
import com.zup.mercado.config.validator.CustomBusinessRuleViolation;
import com.zup.mercado.config.validator.CustomNotFoundException;
import com.zup.mercado.produto.Produto;
import com.zup.mercado.produto.ProdutoRepository;
import com.zup.mercado.utils.EmailSender;
import com.zup.mercado.utils.EmailSenderFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/produtos")
public class CompraController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private EmailSenderFake emailSender;


    @PostMapping(value = "/{id}/compra")
    @Transactional
    public ResponseEntity<CompraResponse> adquireProduto(@PathVariable("id") Long id, @RequestBody @Valid CompraRequest request) {
        Usuario comprador = (Usuario) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("id", "Este produto não existe."));

        //verifica se existe estoque disponível
        if (request.getQuantidade() > produto.getEstoqueProduto()) {
            throw new CustomBusinessRuleViolation("quantidade do produto", "Não há estoque suficiente para esta compra");
        }

        Compra compra = request.toModel(produto, comprador);
        //Abate estoque
        compra.controleEstoque(produto, produtoRepository);
        compraRepository.save(compra);
        //Chamar Gateway de Pagamento
        compra.getGateway().getGatewayPagamento().gerarPagamento(compra);
        emailSender.comunicaCompraRealizada(compra);
        System.out.println("/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/");
        CompraResponse response = new CompraResponse(compra);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
