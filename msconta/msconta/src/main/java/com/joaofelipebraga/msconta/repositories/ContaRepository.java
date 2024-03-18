package com.joaofelipebraga.msconta.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaofelipebraga.msconta.entities.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

	Optional<Conta> findByNumero(String numero);
	
	List<Conta> findByClienteId(Long clienteId);

}
