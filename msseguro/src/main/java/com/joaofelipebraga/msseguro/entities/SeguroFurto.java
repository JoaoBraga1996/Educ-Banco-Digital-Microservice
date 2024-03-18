package com.joaofelipebraga.msseguro.entities;

import java.time.Instant;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("furto")
public class SeguroFurto extends Seguro {

	private String itemCoberto;

	public SeguroFurto() {
	}

	public SeguroFurto(Long id, Instant dataContratacao, String numeroApolice, Double valorApolice,
			String descricaoCondicao, String itemCoberto) {
		super(id, dataContratacao, numeroApolice, valorApolice, descricaoCondicao);
		this.itemCoberto = itemCoberto;

	}

	public String getItemCoberto() {
		return itemCoberto;
	}

	public void setItemCoberto(String itemCoberto) {
		this.itemCoberto = itemCoberto;
	}

}
