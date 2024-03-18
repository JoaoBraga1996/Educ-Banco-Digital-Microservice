package com.joaofelipebraga.msseguro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joaofelipebraga.msseguro.dtos.DadosEmissaoCartao;
import com.joaofelipebraga.msseguro.entities.Seguro;
import com.joaofelipebraga.msseguro.entities.SeguroFurto;
import com.joaofelipebraga.msseguro.entities.SeguroRequest;
import com.joaofelipebraga.msseguro.entities.SeguroVida;
import com.joaofelipebraga.msseguro.mqueue.EmissaoCartaoPublisher;
import com.joaofelipebraga.msseguro.repositories.SeguroRepository;
import com.joaofelipebraga.msseguro.services.exceptions.DatabaseException;
import com.joaofelipebraga.msseguro.services.exceptions.ResourceNotFoundException;

@Service
public class SeguroService {

	@Autowired
	private SeguroRepository repository;

	@Autowired
	private EmissaoCartaoPublisher emissaoCartao;

	@Transactional(readOnly = true)
	public Page<Seguro> findAllPaged(Pageable pageable) {
		Page<Seguro> list = repository.findAll(pageable);
		return list;
	}

	public Seguro insert(Long cartaoId, String tipoSeguro, SeguroRequest request) throws JsonProcessingException {

		DadosEmissaoCartao dadosEmissaoCartao = new DadosEmissaoCartao(cartaoId);
		solicitarEmissaoCartao(dadosEmissaoCartao);

		Seguro entity;

		if (tipoSeguro.equalsIgnoreCase("furto")) {
			entity = new SeguroFurto();
			((SeguroFurto) entity).setItemCoberto(request.getItemCoberto());
			((SeguroFurto) entity).setValorApolice(30.0);
			((SeguroFurto) entity).setDescricaoCondicao(
					"Cobertura em caso de furto do item segurado. Requer apresentação de relatório policial. Não cobre perdas devido a negligência ou furto por conhecidos.");
		} else if (tipoSeguro.equalsIgnoreCase("vida")) {
			entity = new SeguroVida();
			((SeguroVida) entity).setNomeBeneficiario(request.getNomeBeneficiario());
			((SeguroVida) entity).setValorApolice(25.00);
			((SeguroVida) entity).setDescricaoCondicao(
					"Cobertura em caso tenha morte por acidente ou natural, requer atestado de obito");
		} else {
			throw new DatabaseException("Tipo de seguro inválido");
		}

		entity.setCartaoId(dadosEmissaoCartao.getId());
		entity.setDataContratacao();
		entity.setNumeroApolice();

		return repository.save(entity);
	}

	public void update(String tipoSeguro, Seguro request) {
		List<Seguro> seguros = repository.findAll();

		for (Seguro seguro : seguros) {
			if (seguro instanceof SeguroFurto && tipoSeguro.equalsIgnoreCase("furto")) {
				((SeguroFurto) seguro).setValorApolice(request.getValorApolice());
			} else if (seguro instanceof SeguroVida && tipoSeguro.equalsIgnoreCase("vida")) {
				((SeguroVida) seguro).setValorApolice(request.getValorApolice());
			}
		}

		repository.saveAll(seguros);

	}

	public void solicitarEmissaoCartao(DadosEmissaoCartao dados) throws ResourceNotFoundException {
	    try {
	        emissaoCartao.solicitarCartao(dados);
	    } catch (Exception e) {
	        // Rethrow the exception as ResourceNotFoundException
	        throw new ResourceNotFoundException("Failed to process request for card issuance");
	    }
	}

}
