package com.zup.mercado.config.security.usuarios;

public class PerfilResponse {

	private Long id;
	private String nome;

	public PerfilResponse(Perfil perfil) {
		this.id = perfil.getId();
		this.nome = perfil.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}
