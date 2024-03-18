package com.joaofelipebraga.msseguro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaofelipebraga.msseguro.entities.Seguro;

@Repository
public interface SeguroRepository extends JpaRepository<Seguro, Long> {
	

}
