package com.joaofelipebraga.msconta.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaofelipebraga.msconta.dtos.CartaoAtualizarDTO;
import com.joaofelipebraga.msconta.dtos.CartaoCriarDTO;
import com.joaofelipebraga.msconta.dtos.CartaoDTO;
import com.joaofelipebraga.msconta.services.CartaoService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/cartoes")
public class CartaoController {

	@Autowired
	CartaoService service;

	@GetMapping
	public ResponseEntity<Page<CartaoDTO>> findAll(Pageable pagable) {
		Page<CartaoDTO> lista = service.findAllPaged(pagable);
		return ResponseEntity.ok(lista);

	}

	@GetMapping(value = "/{numero}")
	public ResponseEntity<CartaoDTO> findById(@PathVariable String numero) {
		CartaoDTO dto = service.findByNumero(numero);
		return ResponseEntity.ok(dto);

	}

	@PostMapping(value = "/{tipoCartao}")
	public ResponseEntity<CartaoDTO> insert(@PathParam(value = "contaId") Long contaId, @PathVariable String tipoCartao,
			@RequestBody CartaoCriarDTO dto) {
		CartaoDTO newDto = service.insert(contaId, tipoCartao, dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CartaoDTO> update(@PathVariable Long id, @RequestBody CartaoAtualizarDTO dto) {
		CartaoDTO newDto = service.update(id, dto);
		return ResponseEntity.ok().body(newDto);
	}

}
