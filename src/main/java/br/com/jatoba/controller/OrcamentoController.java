package br.com.jatoba.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jatoba.formDto.OrcamentoFormDto;
import br.com.jatoba.modelo.Orcamento;
import br.com.jatoba.modelo.Produto;
import br.com.jatoba.repository.ImagemRepository;
import br.com.jatoba.repository.OrcamentoRepository;
import br.com.jatoba.repository.ProdutoRepository;
import br.com.jatoba.services.EmailService;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {
	
	@Autowired
	OrcamentoRepository orcamentorepo;
	
	@Autowired
	ProdutoRepository produtoRepo;
	
	@Autowired
	ImagemRepository imagemRepo;

	@Autowired
	EmailService emailService;

	Logger logger = LoggerFactory.getLogger(OrcamentoController.class);
	
	@PostMapping
	public ResponseEntity<Orcamento> cadastrar (@RequestBody OrcamentoFormDto orcamentoForm){
		
		Orcamento orcamento = orcamentoForm.convert(imagemRepo, produtoRepo);
		Produto produto = orcamentoForm.getOneProdutoFromProdutoIds(produtoRepo);
		
		orcamentorepo.save(orcamento);

	    //emailService.sendEmail(orcamento, produto);
		
		return ResponseEntity.ok().body(orcamento);
	}
	
	@GetMapping
	public List<Orcamento> listar(){
		
		List<Orcamento> orcamentos = orcamentorepo.findTop50ByOrderByIdDesc();
		
		return orcamentos;
	}

}
