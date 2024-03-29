package com.joaofelipebraga.msconta.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaofelipebraga.msconta.dtos.ContaDTO;
import com.joaofelipebraga.msconta.dtos.ObterContaCliente;
import com.joaofelipebraga.msconta.services.ContaService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaService service;

	@GetMapping
	public ResponseEntity<Page<ContaDTO>> findAll(Pageable pageable) {
		Page<ContaDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{numero}")
	public ResponseEntity<ContaDTO> findByNumero(@PathVariable String numero) {
		ContaDTO dto = service.findByNumero(numero);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping("/clientes/{clienteId}")
	public ResponseEntity<ObterContaCliente> findContaByClienteId(@PathVariable Long clienteId) {
		ObterContaCliente dto = service.encontrarCartoesPorClienteId(clienteId);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping(value = "/{tipoConta}")
	public ResponseEntity<ContaDTO> insert(@PathParam(value = "clienteId") Long clienteId,
			@PathVariable String tipoConta) {
		ContaDTO dto = service.insert(clienteId, tipoConta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

}
