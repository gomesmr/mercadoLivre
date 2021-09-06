package com.zup.mercado.config.security.usuarios;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UsuariosPerfis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long usuario;
    private Long perfil;

    public UsuariosPerfis() {
    }

    public UsuariosPerfis(Long usuario, Long perfil) {
        this.usuario = usuario;
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuario() {
        return usuario;
    }

    public Long getPerfil() {
        return perfil;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public void setPerfil(Long perfil) {
        this.perfil = perfil;
    }
}
