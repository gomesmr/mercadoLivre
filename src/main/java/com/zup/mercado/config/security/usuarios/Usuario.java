package com.zup.mercado.config.security.usuarios;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank @Email
	private String email;

	private String senha;
	private LocalDateTime dataHoraCadastro;

	private boolean contaNaoExpirada = true;
	private boolean contaNaoBloqueada = true;
	private boolean credencialNaoExpirada = true;
	private boolean	contaAtiva = true;

	@ManyToMany(fetch = FetchType.EAGER,
			cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	}

	)
	@JoinTable(name = "usuario_perfis",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "perfis_id")
	)
	private List<Perfil> perfis = new ArrayList<>();


	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil);
		perfil.getUsuarios().add(this);
	}

	@Deprecated
	public Usuario() {
	}

	public Usuario(Long id, String email, String senha, LocalDateTime dataHoraCadastro,
				   boolean contaNaoExpirada, boolean  contaNaoBloqueada,
				   boolean  credencialNaoExpirada, boolean  contaAtiva) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.dataHoraCadastro = dataHoraCadastro;

		this.contaNaoExpirada = contaNaoExpirada;
		this.contaNaoBloqueada = contaNaoBloqueada;
		this.credencialNaoExpirada = credencialNaoExpirada;
		this.contaAtiva = contaAtiva;
	}

	public Usuario(String email, String senha) {
		Assert.hasLength(email, "O e-mail é obrigatório");
		this.email = email;
		this.senha = senha;

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

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public  LocalDateTime getDataHoraCadastro() {
		return dataHoraCadastro;
	}


	@Override
	public Collection<? extends GrantedAuthority> 	getAuthorities() {
		return this.perfis;
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
		return this.contaNaoExpirada;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.contaNaoBloqueada;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credencialNaoExpirada;
	}

	@Override
	public boolean isEnabled() {
		return this.contaAtiva;
	}

}
