package com.joaofelipebraga.msconta.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaofelipebraga.msconta.dtos.ClienteDTO;
import com.joaofelipebraga.msconta.dtos.ContaDTO;
import com.joaofelipebraga.msconta.dtos.ObterContaCliente;
import com.joaofelipebraga.msconta.entities.Conta;
import com.joaofelipebraga.msconta.entities.ContaCorrente;
import com.joaofelipebraga.msconta.entities.ContaPoupanca;
import com.joaofelipebraga.msconta.feign.ClienteFeign;
import com.joaofelipebraga.msconta.repositories.ContaRepository;
import com.joaofelipebraga.msconta.services.exceptions.InvalidRequestException;
import com.joaofelipebraga.msconta.services.exceptions.ResourceNotFoundException;

import feign.FeignException;

@Service
public class ContaService {

	@Autowired
	ContaRepository repository;

	@Autowired
	ClienteFeign clientefeign;

	@Transactional(readOnly = true)
	public Page<ContaDTO> findAllPaged(Pageable pageable) {
		Page<Conta> list = repository.findAll(pageable);
		return list.map(x -> new ContaDTO(x));
	}

	@Transactional(readOnly = true)
	public ContaDTO findByNumero(String numero) {
		Optional<Conta> obj = repository.findByNumero(numero);
		Conta entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não achada"));
		return new ContaDTO(entity, entity.getCartoes());
	}

	@Transactional(readOnly = true)
	public ObterContaCliente encontrarCartoesPorClienteId(Long clienteId) {
		List<Conta> contas = repository.findByClienteId(clienteId); 
		ObterContaCliente obterContaCliente = new ObterContaCliente();
		obterContaCliente.setClienteId(clienteId);
		List<ContaDTO> contasDTO = contas.stream().map(ContaDTO::new).collect(Collectors.toList());
		obterContaCliente.setContas(contasDTO);
		return obterContaCliente;
	}

	@Transactional
	public ContaDTO insert(Long clienteId, String tipoConta) {

		try {

			ResponseEntity<ClienteDTO> cliente = clientefeign.findById(clienteId);
			Conta entity;

			if ("corrente".equalsIgnoreCase(tipoConta)) {
				entity = new ContaCorrente();
			} else if ("poupanca".equalsIgnoreCase(tipoConta)) {
				entity = new ContaPoupanca();
			} else {
				throw new InvalidRequestException("Tipo de conta inválido: " + tipoConta);
			}

			entity.setClienteId(cliente.getBody().getId());
			entity.setAgencia();
			entity.setNumero();
			entity = repository.save(entity);
			return new ContaDTO(entity);

		} catch (FeignException.NotFound ex) {
			throw new ResourceNotFoundException("Cliente não encontrado");
		}
	}
}
