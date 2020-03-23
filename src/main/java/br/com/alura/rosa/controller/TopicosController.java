package br.com.alura.rosa.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.rosa.controller.dto.DetalhesTopicoDto;
import br.com.alura.rosa.controller.dto.TopicoDto;
import br.com.alura.rosa.controller.form.AtualizarTopicoFormDto;
import br.com.alura.rosa.controller.form.TopicoFormDto;
import br.com.alura.rosa.modelo.Topico;
import br.com.alura.rosa.repository.CursoRepository;
import br.com.alura.rosa.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	@ResponseBody
	@Cacheable(value = "listaDeTopicos")
	public Page<TopicoDto> listar(
			@RequestParam(required = false) String nomeCurso, @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 5) Pageable paginacao) //para funcionar assim, tem habilitar um modo no form application
	{
		//na url, utilizar page, size e sort
		if (nomeCurso == null) {

			Page<Topico> topicos = topicoRepository.findAll(paginacao); //o page tera além da lista de registros oa informações sobre paginacao
			return TopicoDto.converter(topicos);

		}  else {

			Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
			return TopicoDto.converter(topicos);
		}

	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true) //limpa o cache da pesquisa quando algo for alterado
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoFormDto form,
			UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesTopicoDto> detalhar(@PathVariable Long id) { // o path variable serve para indicar que
																				// o id é id passado na url

		Optional<Topico> topico = topicoRepository.findById(id);

		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesTopicoDto(topico.get()));

		} else {
			return ResponseEntity.notFound().build();

		}

	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true) //limpa o cache da pesquisa quando algo for alterado
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarTopicoFormDto form) {

		Topico topico = form.atualizar(id, topicoRepository);

		return ResponseEntity.ok(new TopicoDto(topico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true) //limpa o cache da pesquisa quando algo for alterado
	public ResponseEntity deletar(@PathVariable Long id) {

		topicoRepository.deleteById(id);

		return ResponseEntity.ok("Registro deletado com sucesso");

	}

}
