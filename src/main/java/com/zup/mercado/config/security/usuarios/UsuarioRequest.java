package com.zup.mercado.config.security.usuarios;
import com.zup.mercado.config.validator.UniqueValue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UsuarioRequest {

	private static final long serialVersionUID = 1L;

	@UniqueValue(domainClass = Usuario.class, fieldName = "email", message = "Já existe um usuário com este email cadastrado.")
	private @NotBlank @Email String email;
	private @NotBlank @Size(min = 6) String senha;
	private @NotNull LocalDateTime dataHoraCadastro;

	public UsuarioRequest(String email, String senha) {
		this.email = email;
		this.senha = senha;
		this.dataHoraCadastro = LocalDateTime.now();
	}

	Usuario toModel(){
		Assert.hasLength(email, "O e-mail é obrigatório");
		Assert.hasLength(senha, "A senha é obrigatória");
		Usuario u = new Usuario(
		this.email,
		new BCryptPasswordEncoder().encode(this.senha));
		return u;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}
}
