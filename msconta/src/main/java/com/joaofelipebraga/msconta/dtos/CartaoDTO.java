package com.joaofelipebraga.msconta.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joaofelipebraga.msconta.entities.Cartao;
import com.joaofelipebraga.msconta.entities.CartaoCredito;
import com.joaofelipebraga.msconta.entities.CartaoDebito;
import com.joaofelipebraga.msconta.entities.enums.Status;

public class CartaoDTO {

	private Long id;
	private String numero;
	private Status status;;
	private BigDecimal limiteCredito;

	@JsonProperty(value = "saldoDevedor")
	private BigDecimal limiteUtilizado;

	private BigDecimal limiteDiario;
	private BigDecimal limiteDiarioUtilizado;
	private BigDecimal saldo;

	public CartaoDTO() {
	}

	public CartaoDTO(Long id, String numero, Status status, BigDecimal limiteCredito, BigDecimal limiteUtilizado,
			BigDecimal limiteDiario, BigDecimal limiteDiarioUtilizado, BigDecimal saldo) {

		this.id = id;
		this.numero = numero;
		this.status = status;
		this.limiteCredito = limiteCredito;
		this.limiteUtilizado = limiteUtilizado;
		this.limiteDiario = limiteDiario;
		this.limiteDiarioUtilizado = limiteDiarioUtilizado;
		this.saldo = saldo;
	}

	public CartaoDTO(Cartao entity) {

		this.id = entity.getId();
		this.numero = entity.getNumero();
		this.status = entity.getStatus();
		this.limiteDiario = entity.getLimiteDiario();
		this.limiteDiarioUtilizado = entity.getLimiteDiarioUtilizado();
		if (entity instanceof CartaoDebito) {
			this.saldo = ((CartaoDebito) entity).getSaldo();
		} else if (entity instanceof CartaoCredito) {
			this.limiteCredito = ((CartaoCredito) entity).getLimiteCredito();
			this.limiteUtilizado = ((CartaoCredito) entity).getLimiteUtilizado();

		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public BigDecimal getLimiteUtilizado() {
		return limiteUtilizado;
	}

	public void setLimiteUtilizado(BigDecimal limiteUtilizado) {
		this.limiteUtilizado = limiteUtilizado;
	}

	public BigDecimal getLimiteDiario() {
		return limiteDiario;
	}

	public void setLimiteDiario(BigDecimal limiteDiario) {
		this.limiteDiario = limiteDiario;
	}

	public BigDecimal getLimiteDiarioUtilizado() {
		return limiteDiarioUtilizado;
	}

	public void setLimiteDiarioUtilizado(BigDecimal limiteDiarioUtilizado) {
		this.limiteDiarioUtilizado = limiteDiarioUtilizado;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
