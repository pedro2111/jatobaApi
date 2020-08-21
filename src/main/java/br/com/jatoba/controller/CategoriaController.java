package br.com.jatoba.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jatoba.modelo.Categoria;
import br.com.jatoba.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	@ResponseBody
	public List<Categoria> listar (){
		
		List<Categoria> categorias = categoriaRepository.listarPorNome();
		
		return categorias;
	}
	
	@PostMapping
	public ResponseEntity<Categoria> cadastrar (@RequestBody @Valid Categoria categoria,UriComponentsBuilder uriBuilder){
		
		categoriaRepository.save(categoria);
		
		URI uri = uriBuilder.path("categorias/{id}").buildAndExpand(categoria.getId()).toUri();
		
				
		return ResponseEntity.created(uri).body(categoria);
		
	}
	
	@GetMapping("/{id}")
	public Categoria detalhar(@PathVariable Long id) {
		
		Categoria categoria = categoriaRepository.getOne(id);
		
		return categoria;
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Categoria> atualizar (@PathVariable Long id, @RequestBody @Valid Categoria categoria){
		
		Categoria categoriaAtualizada = categoriaRepository.getOne(id);
		
		categoriaAtualizada.setNome(categoria.getNome());
		
		return ResponseEntity.ok(categoriaAtualizada);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar (@PathVariable Long id){
		
		categoriaRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
		
	}
	

}
