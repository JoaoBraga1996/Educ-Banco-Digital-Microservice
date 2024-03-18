package com.joaofelipebraga.msconta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaofelipebraga.msconta.dtos.CompraRealizadaRequestDTO;
import com.joaofelipebraga.msconta.entities.Cartao;
import com.joaofelipebraga.msconta.entities.CompraRealizada;
import com.joaofelipebraga.msconta.repositories.CartaoRepository;
import com.joaofelipebraga.msconta.repositories.CompraRealizadaRepository;
import com.joaofelipebraga.msconta.services.exceptions.DailyLimitExceededException;
import com.joaofelipebraga.msconta.services.exceptions.InsufficientBalanceException;
import com.joaofelipebraga.msconta.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class RealizaCompraService {

	@Autowired
	CartaoRepository cartaoRepository;

	@Autowired
	CompraRealizadaRepository repository;

	@Transactional
	public CompraRealizada realizarCompra(Long cartaoIdOrigem, CompraRealizadaRequestDTO dto) {
		Cartao cartaoOrigem = cartaoRepository.findById(cartaoIdOrigem)
				.orElseThrow(() -> new ResourceNotFoundException("Conta de origem não encontrada"));

		try {
			cartaoOrigem.realizarPagamento(dto.getValor());
			cartaoRepository.save(cartaoOrigem);

			CompraRealizada compraRealizada = new CompraRealizada();
			compraRealizada.setIdCartaoOrigem(cartaoIdOrigem);
			compraRealizada.setValor(dto.getValor());
			repository.save(compraRealizada);

			return compraRealizada;
		} catch (IllegalArgumentException e) {
			throw new DailyLimitExceededException("Limite Diario Ultrapassado !");
		} catch (IllegalStateException e) {
			throw new InsufficientBalanceException("Saldo Insuficiente");
		}
	}

}