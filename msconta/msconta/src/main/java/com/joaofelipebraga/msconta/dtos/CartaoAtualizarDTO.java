package com.joaofelipebraga.msconta.dtos;

public class CartaoAtualizarDTO extends CartaoDTO {

	public CartaoAtualizarDTO() {
		super();
	}

	String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
