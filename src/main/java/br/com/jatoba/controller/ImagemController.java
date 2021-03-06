package br.com.jatoba.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.jatoba.modelo.Imagem;
import br.com.jatoba.modelo.Produto;
import br.com.jatoba.modelo.TipoImagem;
import br.com.jatoba.repository.ImagemRepository;
import br.com.jatoba.repository.ProdutoRepository;
import br.com.jatoba.services.CloudinaryService;

@RestController
@RequestMapping("/imagens")
public class ImagemController {
	
	@Autowired
	CloudinaryService cloudService;
	
	@Autowired
	ImagemRepository imagemRepo;
	
	@Autowired
	ProdutoRepository produtoRepo;
	
	Logger logger = LoggerFactory.getLogger(ImagemController.class);
	
	@PostMapping("/upload")
	public ResponseEntity<String> upload (@RequestParam("multipartfile") MultipartFile[] multipartfiles, @RequestParam("tipo") TipoImagem tipo, @RequestParam("produto_id") Long produto_id) throws IOException{
		
		
		for(int i=0; i<multipartfiles.length; i++) {
			
			MultipartFile multipartfile = multipartfiles[i];
			
			BufferedImage bi = ImageIO.read(multipartfile.getInputStream());
			
			String folder = "";
			
			if(bi == null) {
				
				return ResponseEntity.badRequest().body("imagem inválida");
			}
			
			Produto produto = produtoRepo.getOne(produto_id);
						
			folder = cloudService.setFolder(produto);
			
			Map result = cloudService.upload(multipartfile, folder);
			logger.info(result.toString());
			
			this.logger.info(result.toString());
			
			Imagem imagem = new Imagem((String)result.get("original_filename"), (String)result.get("public_id"), (String)result.get("url"), tipo, produto);
			
			imagemRepo.save(imagem);
		}
			
		
		return ResponseEntity.ok().build();	
		
		
	}
	@GetMapping
	public List<Imagem> listar() {
		
		List<Imagem> imagens = imagemRepo.findCapa();
		
		return imagens;
		
	}
	@GetMapping("/{id}")
	public List<Imagem> listarById (@PathVariable Long id){
		
		Produto produto  = produtoRepo.getOne(id);
		
		List<Imagem> imagens = imagemRepo.findByProduto(produto);
		return imagens;
	}
	
	@GetMapping("/produtoCapa/{id}")
	public Imagem listarByIdCapa (@PathVariable Long id){
		
		Produto produto  = produtoRepo.getOne(id);
		
		Imagem imagens = imagemRepo.findByProdutoCapa(produto);
		return imagens;
	}
	
	@GetMapping("/paginada")
	public Page<Imagem> listarPaginado(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao, @RequestParam(required = false) List<Long> categoria){
		
		

		Page<Imagem> imagens = imagemRepo.findCapaPaginacao(paginacao,categoria);
		
		
		return imagens;
	}
	
	@GetMapping("/mainProdutosMaisCurtidos")
	public Page<Imagem> listarMainProdutos(@PageableDefault(size = 3) Pageable paginacao){

		Page<Imagem> mainProdutos = imagemRepo.findMainProdutos(paginacao);
		
		return mainProdutos;
	}
	
	@GetMapping("/mainUltimosProdutos")
	public Page<Imagem> listarMainUltimosProdutos(@PageableDefault(size = 3) Pageable paginacao){

		Page<Imagem> mainProdutos = imagemRepo.findMainUltimosProdutos(paginacao);
		
		return mainProdutos;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map> delete(@PathVariable("id") Long id) throws IOException  {
		
		Imagem img = imagemRepo.getOne(id);	
		
		Map result = cloudService.delete(img.getPublic_id());
		
		imagemRepo.delete(img);
		
		return ResponseEntity.ok(result);
	}

}

























