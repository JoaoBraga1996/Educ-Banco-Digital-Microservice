package com.joaofelipebraga.msconta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaofelipebraga.msconta.entities.TransferenciaPix;

@Repository
public interface TransferenciaPixRepository extends JpaRepository<TransferenciaPix, Long> {

}
