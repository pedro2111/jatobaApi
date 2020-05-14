package br.com.alura.rosa.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.rosa.controller.dto.ContaDto;
import br.com.alura.rosa.controller.form.ContaFormDto;
import br.com.alura.rosa.modelo.Conta;
import br.com.alura.rosa.repository.ClienteRepository;
import br.com.alura.rosa.repository.ContaRepository;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<ContaDto> listar (){
		
		List<Conta> contas = contaRepository.findAll();
		
		return ContaDto.converter(contas);
	}
	
	@PostMapping
	public ResponseEntity<ContaDto> cadastrar (@RequestBody @Valid ContaFormDto contaForm,UriComponentsBuilder uriBuilder){
		
		Conta conta = contaForm.converter(clienteRepository);
		contaRepository.save(conta);
		
		URI uri = uriBuilder.path("contas/{id}").buildAndExpand(conta.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ContaDto(conta));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		contaRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
		
	}

}
