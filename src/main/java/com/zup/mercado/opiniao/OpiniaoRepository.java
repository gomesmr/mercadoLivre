package com.zup.mercado.opiniao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpiniaoRepository extends JpaRepository <Opiniao, Long> {
}
