package com.joaofelipebraga.msconta.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaofelipebraga.msconta.dtos.CompraRealizadaRequestDTO;
import com.joaofelipebraga.msconta.entities.CompraRealizada;
import com.joaofelipebraga.msconta.services.RealizaCompraService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraRealizadaController {

	@Autowired
	private RealizaCompraService service;

	@PostMapping("/{cartaoIdOrigem}")
	public ResponseEntity<CompraRealizada> insert(@PathVariable @Valid Long cartaoIdOrigem,
			@RequestBody CompraRealizadaRequestDTO request) {
		CompraRealizada compra = service.realizarCompra(cartaoIdOrigem, request);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{contaIdOrigem}")
				.buildAndExpand(compra.getIdCartaoOrigem()).toUri();
		return ResponseEntity.created(uri).body(compra);
	}

}
