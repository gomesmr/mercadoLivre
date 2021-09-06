package com.zup.mercado.config.security.usuario;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Table(name = "perfil")
@Entity
public class Perfil implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}