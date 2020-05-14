package br.com.alura.rosa.controller;

import java.net.URI;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.rosa.modelo.Cliente;
import br.com.alura.rosa.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<Cliente> listar() {
		
		List<Cliente> clientes = clienteRepository.findByOrderByNomeAsc();
		
		return clientes;
		
	}
	
	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente,UriComponentsBuilder uriBuilder){
		
		clienteRepository.save(cliente);
		
		URI uri = uriBuilder.path("clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		
		
		return ResponseEntity.created(uri).body(cliente);
	}
	
	@GetMapping("/{id}")
	public Cliente detalhar(@PathVariable Long id) {
		
		Cliente cliente = clienteRepository.getOne(id);
		
		return cliente;
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody @Valid Cliente cliente){
		
		Cliente clienteAtualizado = clienteRepository.getOne(id);
		
		clienteAtualizado.setNome(cliente.getNome());
		
		return ResponseEntity.ok(clienteAtualizado);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar (@PathVariable Long id){
		
		clienteRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
		
	}

}


















