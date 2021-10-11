package com.zup.mercado.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Modifying
    @Query("update Produto p set p.estoqueProduto = ?1 where p.id = ?2")
    int setEstoqueProdutoById(int estoqueProduto, Long id);
}
