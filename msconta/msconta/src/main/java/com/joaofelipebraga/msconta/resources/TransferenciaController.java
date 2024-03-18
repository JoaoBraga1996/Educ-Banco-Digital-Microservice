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

import com.joaofelipebraga.msconta.entities.TransferenciaPix;
import com.joaofelipebraga.msconta.services.TransferenciaPixService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/transferencia")
@Tag(name = "Endpoint de transferência ", description = "você precisa passar contaOrigem via URL, e a contaDestino e valor por corpo de requisição")
public class TransferenciaController {

	@Autowired
	private TransferenciaPixService service;

	@PostMapping("/{contaIdOrigem}")
	public ResponseEntity<TransferenciaPix> insert(@PathVariable Long contaIdOrigem,
			@RequestBody TransferenciaPix request) {
		TransferenciaPix pix = service.transferirPix(contaIdOrigem, request);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{contaIdOrigem}").buildAndExpand(pix.getId()).toUri();
		return ResponseEntity.created(uri).body(pix);
	}

}
