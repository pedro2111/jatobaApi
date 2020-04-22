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

import br.com.alura.rosa.modelo.Fornecedor;
import br.com.alura.rosa.repository.FornecedorRepository;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@GetMapping
	public List<Fornecedor> listar (){
		
		List<Fornecedor> fornecedores = fornecedorRepository.findAll();
		return fornecedores;
	}
	
	@PostMapping
	public ResponseEntity<Fornecedor> cadastrar(@RequestBody @Valid Fornecedor fornecedor,UriComponentsBuilder uriBuilder){
		
		fornecedorRepository.save(fornecedor);
		
		URI uri = uriBuilder.path("fornecedores/{id}").buildAndExpand(fornecedor.getId()).toUri();
		
		
		return ResponseEntity.created(uri).body(fornecedor);
		
	}
	
	@GetMapping("/{id}")
	public Fornecedor detalhar(@PathVariable Long id) {
		
		Fornecedor fornecedor = fornecedorRepository.getOne(id);
		
		return fornecedor;
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Fornecedor> atualizar (@PathVariable Long id, @RequestBody @Valid Fornecedor fornecedor){
		
		Fornecedor fornecedorAtualizado = fornecedorRepository.getOne(id);
		
		fornecedorAtualizado.setNome(fornecedor.getNome());
		
		return ResponseEntity.ok(fornecedorAtualizado);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar (@PathVariable Long id){
		
		fornecedorRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
		
	}
	
	

}
