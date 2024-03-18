package com.joaofelipebraga.msconta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.joaofelipebraga.msconta.dtos.ClienteDTO;

@FeignClient(value = "mscliente", path = "/clientes")
public interface ClienteFeign {

	@GetMapping(value = "/{id}")
	ResponseEntity<ClienteDTO> findById(@PathVariable Long id);

}
