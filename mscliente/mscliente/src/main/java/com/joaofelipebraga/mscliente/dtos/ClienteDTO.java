
package com.joaofelipebraga.mscliente.dtos;

import com.joaofelipebraga.mscliente.entities.Cliente;
import com.joaofelipebraga.mscliente.entities.Endereco;
import com.joaofelipebraga.mscliente.entities.enums.Categoria;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class ClienteDTO {

	private Long id;

	@Size(min = 10, max = 60)
	private String nome;

	@Email
	private String email;
	private Categoria categoria;
	private Endereco endereco;

	public ClienteDTO() {
	}

	public ClienteDTO(Long id, String nome, String email, Categoria categoria, Endereco endereco) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.categoria = categoria;
		this.endereco = endereco;
	}

	public ClienteDTO(Cliente entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
		this.categoria = entity.getCategoria();
		this.endereco = entity.getEndereco();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
