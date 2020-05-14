package br.com.alura.rosa.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.rosa.controller.dto.MovimentacaoDto;
import br.com.alura.rosa.controller.form.MovimentacaoFormDto;
import br.com.alura.rosa.modelo.Cliente;
import br.com.alura.rosa.modelo.Movimentacao;
import br.com.alura.rosa.repository.ClienteRepository;
import br.com.alura.rosa.repository.ContaRepository;
import br.com.alura.rosa.repository.MovimentacaoRepository;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {
	
	@Autowired
	ClienteRepository cliRepo;
	
	@Autowired
	ContaRepository contaRepo;
	
	@Autowired
	MovimentacaoRepository movRepo;
	
	
	@PostMapping
	public ResponseEntity<Movimentacao> cadastrar(@RequestBody @Valid MovimentacaoFormDto movForm,UriComponentsBuilder uriBuilder){
		
		Movimentacao movimentacao = movForm.converter(cliRepo, contaRepo);
		
		movRepo.save(movimentacao);
		
		URI uri = uriBuilder.path("/movimentacoes/{id}").buildAndExpand(movimentacao.getId()).toUri();
		
		
		return ResponseEntity.created(uri).body(movimentacao);
		
	}
	
	@GetMapping("/listarClientesMovimentacoes")
	public List<?> listarClienteMovimentacao() {
		
		List<?> movimentacoes = movRepo.findMovimentacoesPorCliente();
		
		
		return movimentacoes;
	}
	

}
