package br.com.jatoba.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jatoba.dto.ProdutoDto;
import br.com.jatoba.formDto.ProdutoFormDto;
import br.com.jatoba.modelo.Produto;
import br.com.jatoba.modelo.StatusProduto;
import br.com.jatoba.repository.CategoriaRepository;
import br.com.jatoba.repository.ImagemRepository;
import br.com.jatoba.repository.ProdutoRepository;
import br.com.jatoba.services.CloudinaryService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepo;

	@Autowired
	CategoriaRepository categoriaRepo;

	@Autowired
	ImagemRepository imagemRepo;

	@Autowired
	CloudinaryService cloudService;

	Logger logger = LoggerFactory.getLogger(ProdutoController.class);

	@PostMapping
	public ResponseEntity<Produto> cadastrar(@RequestBody ProdutoFormDto produtoform) {

		Produto produto = produtoform.converter(categoriaRepo);

		produtoRepo.save(produto);

		return ResponseEntity.ok(produto);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody ProdutoFormDto form) {

		Produto produto = form.atualizar(id, produtoRepo);
		produtoRepo.save(produto);

		return ResponseEntity.ok(produto);
	}

	@GetMapping
	public List<Produto> listar() {

		List<Produto> produtos = produtoRepo.findByOrderByIdDesc();

		return produtos;
	}

	@GetMapping("/listarImagens")
	public List<?> listarImagens() {

		List<?> produtos = produtoRepo.findProdutoImagens();

		return produtos;

	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> detalhar(@PathVariable Long id) {

		Produto produto = produtoRepo.getOne(id);

		return ResponseEntity.ok().body(new ProdutoDto(produto));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {

		// Produto produto = produtoRepo.getOne(id);

		// imagemRepo.deleteByProduto(produto);

		produtoRepo.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/trocarStatus/{id}")
	public ResponseEntity<Produto> toogleStatus(@PathVariable Long id) {

		Produto prod = produtoRepo.getOne(id);

		if (prod.getStatus().equals(StatusProduto.ATIVO)) {

			prod.setStatus(StatusProduto.INATIVO);

		} else {

			prod.setStatus(StatusProduto.ATIVO);
		}

		produtoRepo.save(prod);

		return ResponseEntity.ok(prod);
	}

	@PutMapping("/like/{id}")
	public ResponseEntity<Produto> like(@PathVariable Long id) {

		Produto produto = produtoRepo.getOne(id);

		produto.setCurtidas(produto.getCurtidas() + 1);

		produtoRepo.save(produto);

		return ResponseEntity.ok(produto);

	}

}
