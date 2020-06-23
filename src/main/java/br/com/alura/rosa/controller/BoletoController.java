package br.com.alura.rosa.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import br.com.alura.rosa.controller.dto.BoletoDto;
import br.com.alura.rosa.controller.form.BoletoFormDto;
import br.com.alura.rosa.modelo.Boleto;
import br.com.alura.rosa.modelo.StatusBoleto;
import br.com.alura.rosa.repository.BoletoRepository;
import br.com.alura.rosa.repository.CategoriaRepository;
import br.com.alura.rosa.repository.FornecedorRepository;
import br.com.alura.rosa.repository.UsuarioRepository;

@RestController
@RequestMapping("boletos")
public class BoletoController {
	
	@Autowired
	private BoletoRepository boletoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//listar normal, sem paginação
	
	@GetMapping("/listarBoletos")
	@ResponseBody
	@Cacheable(value = "listaDeBoletos")
	public List<BoletoDto> listar (){
		
		List<Boleto> boletos = boletoRepository.findByOrderByIdDesc();
		
		return BoletoDto.converter(boletos);
		
	}
	
	@GetMapping
	@ResponseBody
	@Cacheable(value = "listaDeBoletos")
	public Page<BoletoDto> listarPaginado (@PageableDefault(sort = "status" ,direction = Direction.ASC) Pageable paginacao){
		
		Page<Boleto> boletos = boletoRepository.findAll(paginacao);
		
		return BoletoDto.converterPaginacao(boletos);
		
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = {"listaDeBoletos", "listaVencidos", "listaVencidosHoje", "listaAvencer"}, allEntries = true) //limpa o cache da pesquisa quando algo for alterado
	public ResponseEntity<BoletoDto> cadastrar (@RequestBody @Valid BoletoFormDto form,UriComponentsBuilder uriBuilder){
		
		Boleto boleto = form.converter(usuarioRepository,fornecedorRepository,categoriaRepository);
		
		boletoRepository.save(boleto);
		
		URI uri = uriBuilder.path("boletos/{id}").buildAndExpand(boleto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new BoletoDto(boleto));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BoletoDto> detalhar (@PathVariable Long id){
		
		Optional<Boleto> boleto = boletoRepository.findById(id);
		
		if(boleto.isPresent()) {
			return ResponseEntity.ok(new BoletoDto(boleto.get()));
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = {"listaDeBoletos", "listaVencidos", "listaVencidosHoje", "listaAvencer"}, allEntries = true) //limpa o cache da pesquisa quando algo for alterado
	public ResponseEntity<BoletoDto> atualizar(@PathVariable Long id, @RequestBody @Valid BoletoFormDto form){
		
		Boleto boleto = form.atualizar(id,boletoRepository);
		boletoRepository.save(boleto);
		
		return ResponseEntity.ok(new BoletoDto(boleto));
		
	}
	@PutMapping("/{id}/pagar")
	@Transactional
	@CacheEvict(value = {"listaDeBoletos", "listaVencidos", "listaVencidosHoje", "listaAvencer"}, allEntries = true) //limpa o cache da pesquisa quando algo for alterado
	public ResponseEntity<BoletoDto> pagar (@PathVariable Long id){
		
		Boleto boleto = boletoRepository.getOne(id);
		boleto.setStatus(StatusBoleto.PAGO);
		boletoRepository.save(boleto);
		
		return ResponseEntity.ok(new BoletoDto(boleto));
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = {"listaDeBoletos", "listaVencidos", "listaVencidosHoje", "listaAvencer"}, allEntries = true) //limpa o cache da pesquisa quando algo for alterado
	public ResponseEntity<?> delete (@PathVariable Long id){
		
		boletoRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping("/AVencer")
	@Cacheable(value = "listaAvencer")
	public List<BoletoDto> listarAVencer (){
		
		List<Boleto> boletosFiltrados = boletoRepository.findAVencer();
		
		return BoletoDto.converter(boletosFiltrados);
	}
	
	@GetMapping("/Vencidos")
	@Cacheable(value = "listaVencidos")
	public List<BoletoDto> listarVencidos (){
		
		List<Boleto> boletosFiltrados = boletoRepository.findVencidos();
		
		return BoletoDto.converter(boletosFiltrados);
	}
	
	@GetMapping("/VencidosHoje")
	@Cacheable(value = "listaVencidosHoje")
	public List<BoletoDto> listarVencidosHoje (){
		
		List<Boleto> boletosFiltrados = boletoRepository.findVencidosHoje();
		
		return BoletoDto.converter(boletosFiltrados);
	}
	
	@GetMapping("/totalVencidosHoje")
	public int totalBoletosVencidosHoje() {
		
		int total = boletoRepository.countVencidoHoje();
		return total;
	}
	
	@GetMapping("/totalVencidos")
	public int totalBoletosVencidos() {
		
		int total = boletoRepository.countVencido();
		return total;
	}
	
	@GetMapping("/totalAVencer")
	public int totalBoletosAVencer() {
		
		int total = boletoRepository.countAVencer();
		return total;
	}
	
	@GetMapping("/totalPago")
	public int totalBoletosPago() {
		
		int total = boletoRepository.countPago();
		return total;
	}
	
	@GetMapping("/totalMes")
	public List totalBoletosMes() {
		
		List boletosMes = boletoRepository.countTotalMes();
		return boletosMes;
	}
		
	

}
