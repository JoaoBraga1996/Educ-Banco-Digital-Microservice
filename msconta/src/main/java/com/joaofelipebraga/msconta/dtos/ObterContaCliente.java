package com.joaofelipebraga.msconta.dtos;

import java.util.List;

public class ObterContaCliente {

	public ObterContaCliente() {
	}

	Long clienteId;
	List<ContaDTO> contas;

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public List<ContaDTO> getContas() {
		return contas;
	}

	public void setContas(List<ContaDTO> contas) {
		this.contas = contas;
	}

}
