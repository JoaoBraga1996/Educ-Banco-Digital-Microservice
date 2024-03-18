package com.joaofelipebraga.msconta.dtos;

import jakarta.validation.constraints.NotBlank;

public class ContaCriarOuAtualizarDTO extends ContaDTO {
	
	@NotBlank
	private String senha;
	
	public ContaCriarOuAtualizarDTO() {
		super();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	

}
