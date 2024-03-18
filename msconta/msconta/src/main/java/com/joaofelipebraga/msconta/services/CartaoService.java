package com.joaofelipebraga.msconta.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaofelipebraga.msconta.dtos.CartaoAtualizarDTO;
import com.joaofelipebraga.msconta.dtos.CartaoCriarDTO;
import com.joaofelipebraga.msconta.dtos.CartaoDTO;
import com.joaofelipebraga.msconta.entities.Cartao;
import com.joaofelipebraga.msconta.entities.CartaoCredito;
import com.joaofelipebraga.msconta.entities.CartaoDebito;
import com.joaofelipebraga.msconta.entities.Conta;
import com.joaofelipebraga.msconta.repositories.CartaoRepository;
import com.joaofelipebraga.msconta.repositories.ContaRepository;
import com.joaofelipebraga.msconta.services.exceptions.ResourceNotFoundException;

@Service
public class CartaoService {

	@Autowired
	CartaoRepository repository;

	@Autowired
	ContaRepository contaRepository;

	@Transactional(readOnly = true)
	public Page<CartaoDTO> findAllPaged(Pageable pageable) {
		Page<Cartao> list = repository.findAll(pageable);
		return list.map(x -> new CartaoDTO(x));
	}

	@Transactional(readOnly = true)
	public CartaoDTO findByNumero(String numero) {
		Optional<Cartao> obj = repository.findByNumero(numero);
		Cartao entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não achada"));
		return new CartaoDTO(entity);
	}


	@Transactional
	public CartaoDTO insert(Long contaId, String tipoCartao, CartaoCriarDTO dto) {

		Conta entityConta = contaRepository.findById(contaId)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + contaId));

		Cartao entity;

		if ("cartaoDebito".equalsIgnoreCase(tipoCartao)) {
			entity = new CartaoDebito();

		} else if ("cartaoCredito".equalsIgnoreCase(tipoCartao)) {
			entity = new CartaoCredito();

		} else {
			throw new IllegalArgumentException("Tipo de conta inválido: " + tipoCartao);
		}

		entity.setConta(entityConta);
		entity.setNumero();
		entity.setSenha(dto.getSenha());
		entity = repository.save(entity);

		return new CartaoDTO(entity);
	}

	@Transactional
	public CartaoDTO update(Long id, CartaoAtualizarDTO dto) {
		try {
			Optional<Cartao> obj = repository.findById(id);
			Cartao entity = obj.get();
			entity.setSenha(dto.getSenha());
			entity.setStatus(dto.getStatus());
			entity.setLimiteDiario(dto.getLimiteDiario());

			entity = repository.save(entity);
			return new CartaoDTO(entity);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

}
