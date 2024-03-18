package com.joaofelipebraga.mscliente.dtos;

import com.joaofelipebraga.mscliente.services.validation.ClienteInsertValid;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@ClienteInsertValid
public class ClienteCriarDTO extends ClienteDTO {

	@Column(unique = true)
	@NotBlank
	private String cpf;
 
	@NotBlank
	@Size(min = 4)
	private String senha;

	public ClienteCriarDTO() {
		super();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
