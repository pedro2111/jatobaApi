package br.com.jatoba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jatoba.formDto.OrcamentoFormDto;
import br.com.jatoba.modelo.Orcamento;
import br.com.jatoba.repository.ImagemRepository;
import br.com.jatoba.repository.OrcamentoRepository;
import br.com.jatoba.repository.ProdutoRepository;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {
	
	@Autowired
	OrcamentoRepository orcamentorepo;
	
	@Autowired
	ProdutoRepository produtoRepo;
	
	@Autowired
	ImagemRepository imagemRepo;
	
	@PostMapping
	public ResponseEntity<Orcamento> cadastrar (@RequestBody OrcamentoFormDto orcamentoForm){
		
		Orcamento orcamento = orcamentoForm.convert(imagemRepo, produtoRepo);
		
		orcamentorepo.save(orcamento);
		
		return ResponseEntity.ok().body(orcamento);
	}
	
	@GetMapping
	public List<Orcamento> listar(){
		
		List<Orcamento> orcamentos = orcamentorepo.findTop50ByOrderByIdDesc();
		
		return orcamentos;
	}

}
