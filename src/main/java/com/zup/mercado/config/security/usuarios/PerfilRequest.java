package com.zup.mercado.config.security.usuarios;

public class PerfilRequest {

	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}

	Perfil toModel(){
		Perfil perfil = new Perfil(this.nome);
		return perfil;
	}

}
