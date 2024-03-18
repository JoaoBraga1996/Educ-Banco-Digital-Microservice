package com.joaofelipebraga.msconta.entities;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("corrente")
public class ContaCorrente extends Conta {

	private BigDecimal taxaManutecao = new BigDecimal(15);;

	public ContaCorrente() {
	}

	public ContaCorrente(Long id, String agencia, String numeroConta, BigDecimal saldo) {
		super(id, agencia, numeroConta, saldo);
	}


	public BigDecimal getTaxaManutecao() {
		return taxaManutecao;
	}

	public void setTaxaManutecao(BigDecimal taxaManutecao) {
		this.taxaManutecao = taxaManutecao;
	}

	public void descontarTaxaManutencao() {
		saldo = saldo.subtract(taxaManutecao);

	}

}
