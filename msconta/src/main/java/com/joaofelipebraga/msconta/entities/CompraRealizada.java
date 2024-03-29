package com.joaofelipebraga.msconta.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class CompraRealizada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idCartaoOrigem;
	private BigDecimal valor;
	private Instant dataCompra;

	public CompraRealizada() {
	}

	public CompraRealizada(Long id, Long idCartaoOrigem, BigDecimal valor, Instant dataCompra) {
		super();
		this.id = id;
		this.idCartaoOrigem = idCartaoOrigem;
		this.valor = valor;
		this.dataCompra = dataCompra;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCartaoOrigem() {
		return idCartaoOrigem;
	}

	public void setIdCartaoOrigem(Long idCartaoOrigem) {
		this.idCartaoOrigem = idCartaoOrigem;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Instant getDataCompra() {
		return dataCompra;
	}

	@PrePersist
	public void setDataCompra() {
		this.dataCompra = Instant.now();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompraRealizada other = (CompraRealizada) obj;
		return Objects.equals(id, other.id);
	}

}
