package com.joaofelipebraga.msconta.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaofelipebraga.msconta.dtos.DadosEmissaoCartao;
import com.joaofelipebraga.msconta.entities.Cartao;
import com.joaofelipebraga.msconta.repositories.CartaoRepository;
import com.joaofelipebraga.msconta.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Component
public class EmissaoCartaoSubscriber {

	@Autowired
	CartaoRepository repository;

	@RabbitListener(queues = "${mq.queues.emissao-cartoes}")
	public void receberSolicitacaoEmissao(@Payload String payload) {

		try {
			var mapper = new ObjectMapper();
			DadosEmissaoCartao dados = mapper.readValue(payload, DadosEmissaoCartao.class);
			Cartao cartao = repository.findById(dados.getId())
					.orElseThrow(() -> new EntityNotFoundException("Entidade não achada"));

			repository.save(cartao);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}
