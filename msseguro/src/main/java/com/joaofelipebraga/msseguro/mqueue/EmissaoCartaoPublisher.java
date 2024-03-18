package com.joaofelipebraga.msseguro.mqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaofelipebraga.msseguro.dtos.DadosEmissaoCartao;

@Component
public class EmissaoCartaoPublisher {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Queue queueEmissaoCartoes;

	   public void solicitarCartao(DadosEmissaoCartao dados) throws JsonProcessingException {
		   
	        var json = convertIntoJson(dados);
	        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);
	    }

	    private String convertIntoJson(DadosEmissaoCartao dados) throws JsonProcessingException{
	        ObjectMapper mapper = new ObjectMapper();
	        var json = mapper.writeValueAsString(dados);
	        return json;
	    }
}
