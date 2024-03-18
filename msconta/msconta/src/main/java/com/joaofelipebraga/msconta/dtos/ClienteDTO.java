package com.joaofelipebraga.msconta.dtos;

public class ClienteDTO {

	private Long id;

	public ClienteDTO() {

	}

	public ClienteDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
