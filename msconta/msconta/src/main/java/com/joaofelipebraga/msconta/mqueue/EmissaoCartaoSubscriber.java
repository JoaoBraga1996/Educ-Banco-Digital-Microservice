package com.joaofelipebraga.msconta.mqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaofelipebraga.msconta.dtos.DadosEmissaoCartao;
import com.joaofelipebraga.msconta.entities.Cartao;
import com.joaofelipebraga.msconta.repositories.CartaoRepository;

@Component
public class EmissaoCartaoSubscriber {

    private static final Logger log = LoggerFactory.getLogger(EmissaoCartaoSubscriber.class);

    @Autowired
    CartaoRepository repository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) {
        try {
            var mapper = new ObjectMapper();
            DadosEmissaoCartao dados = mapper.readValue(payload, DadosEmissaoCartao.class);
            Cartao cartao = repository.findById(dados.getId()).orElseThrow();
            repository.save(cartao);
        } catch (Exception e) {
            log.error("Erro ao receber solicitação de emissão de cartão: {}", e.getMessage());
        }
    }
}
