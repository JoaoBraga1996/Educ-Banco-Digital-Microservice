package com.joaofelipebraga.mscliente.services;

import org.springframework.stereotype.Service;

import com.joaofelipebraga.mscliente.entities.Endereco;
import com.joaofelipebraga.mscliente.feign.EnderecoFeign;

@Service
public class EnderecoService {

	private final EnderecoFeign enderecoFeign;

	public EnderecoService(EnderecoFeign enderecoFeign) {
		this.enderecoFeign = enderecoFeign;
	}
	public Endereco executa(String cpf) {
		return enderecoFeign.buscaEnderecoCep(cpf);
	}

}
