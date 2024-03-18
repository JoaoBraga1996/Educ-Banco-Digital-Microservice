package com.joaofelipebraga.msseguro.entities;

import java.time.Instant;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("vida")
public class SeguroVida extends Seguro {

	String nomeBeneficiario;

	public SeguroVida() {
	}

	public SeguroVida(Long id, Instant dataContratacao, String numeroApolice, Double valorApolice,
			String descricaoCondicao, String nomeBeneficiario) {
		super(id, dataContratacao, numeroApolice, valorApolice, descricaoCondicao);
		this.nomeBeneficiario = nomeBeneficiario;
	}

	public String getNomeBeneficiario() {
		return nomeBeneficiario;
	}

	public void setNomeBeneficiario(String nomeBeneficiario) {
		this.nomeBeneficiario = nomeBeneficiario;
	}

}
