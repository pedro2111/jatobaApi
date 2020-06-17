package br.com.alura.rosa.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.com.alura.rosa.controller.dto.MovimentacaoDto;
import br.com.alura.rosa.controller.form.MovimentacaoFormDto;
import br.com.alura.rosa.modelo.Cliente;
import br.com.alura.rosa.modelo.Conta;
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
	
	private static final Logger logger = LoggerFactory.getLogger(Movimentacao.class);
	
	
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
	
	@GetMapping("/listarClientesSemMovimentacoes")
	public List<?> listarClienteSemMovimentacao() {
		
		List<?> movimentacoes = movRepo.findClienteSemMovimentacao();
		
		
		return movimentacoes;
	}
	
	@GetMapping("/listarClientesContaSemMovimentacoes")
	public List<?> listarClienteContaSemMovimentacao() {
		
		List<?> movimentacoes = movRepo.findClienteContaSemMovimentcao();
			
	
		return movimentacoes;
	}
	
	@GetMapping("listarTotalClienteMovimentacoes")
	public List<?> listarTotalClienteMovimentacao(){
		
		List<?> totalMov = movRepo.findTotalClienteMovimentacao();
		
		
		return totalMov;
		
	}
	
	@GetMapping("/{id}/listarMovimentacoesCliente")
	public List<MovimentacaoDto> listarMovimentacoesCliente(@PathVariable Long id){
		
		Cliente cliente = cliRepo.getOne(id);
		
		List<Movimentacao> movimentacoes = movRepo.findMovimentacoesByClienteId(cliente);
		
		return MovimentacaoDto.converter(movimentacoes);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar (@PathVariable Long id){
		
		movRepo.deleteById(id);
		
		return ResponseEntity.ok().build();
		
	}
	@GetMapping("/conta/{id}")
	public List<MovimentacaoDto> listarMovimentacoesConta(@PathVariable Long id) {
		
		Conta conta = contaRepo.getOne(id);
		
		List<Movimentacao> movimentacoes = movRepo.findMovimentacoesByContaId(conta);
		
		return MovimentacaoDto.converter(movimentacoes);
		
	}
	
	
	

}




















