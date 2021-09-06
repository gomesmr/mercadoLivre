package com.zup.mercado.config.security.usuario;

import com.sun.istack.NotNull;
import com.zup.mercado.config.validator.UniqueValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
public class Usuario implements UserDetails {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @UniqueValue
            (domainClass = Usuario.class, fieldName = "email", message = "Já existe um usuário com este email cadastrado.")
    private @NotBlank @Email String email;
    private @NotBlank @Size(min = 6) String senha;
    private @NotNull
    LocalDateTime dataHoraCadastro;


    @Deprecated
    public Usuario() {
    }

    public Usuario(String email, String senha, LocalDateTime dataHoraCadastro) {
        Assert.hasLength(email, "O e-mail é obrigatório");
        Assert.hasLength(senha, "A senha é obrigatória");

        this.email = email;
        this.senha = senha;
        this.dataHoraCadastro = dataHoraCadastro;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Getters
     */
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataHoraCadastro() {
        return dataHoraCadastro;
    }
}
