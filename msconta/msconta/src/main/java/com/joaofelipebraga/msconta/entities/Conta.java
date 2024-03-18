package com.joaofelipebraga.msconta.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String agencia;
	protected String numero;
	protected BigDecimal saldo = BigDecimal.ZERO;
	protected Instant criadoEm;
	protected Long clienteId;

	@OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
	private List<Cartao> cartoes = new ArrayList<>();

	public Conta() {
	}

	public Conta(Long id, String agencia, String numero, BigDecimal saldo) {
		this.id = id;
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = saldo;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia() {
		int numeroAleatorio = new Random().nextInt(10000, 99999);
		this.agencia = String.valueOf(numeroAleatorio);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero() {
		int numeroAleatorio = new Random().nextInt(1000000, 9999999);
		this.numero = String.valueOf(numeroAleatorio);
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public Instant getCriadoEm() {
		return criadoEm;
	}

	@PrePersist
	public void setCriadoEm() {
		this.criadoEm = Instant.now();
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public void transferirPix(Conta destino, BigDecimal valor) {
		if (valor.compareTo(saldo) <= 0) {
			saldo = saldo.subtract(valor);
			destino.saldo = destino.saldo.add(valor);
		} else {
			throw new ArithmeticException();
		}
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
		Conta other = (Conta) obj;
		return Objects.equals(id, other.id);
	}

	public List<Cartao> getCartoes() {
		return cartoes;
	}

}