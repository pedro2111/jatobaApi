package br.com.jatoba.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import br.com.jatoba.controller.dto.UsuarioDto;
import br.com.jatoba.modelo.Usuario;
import br.com.jatoba.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	@ResponseBody
	public List<UsuarioDto> listar(String nome){
		
		if(nome == null) {
			
			List<Usuario> usuarios = usuarioRepository.findAll();
			return UsuarioDto.converter(usuarios);
			
		}else {
			List<Usuario> usuarios = usuarioRepository.findByNome(nome);
			return UsuarioDto.converter(usuarios);
			
		}
		
	}
	@CrossOrigin(origins ="https://restaurantedarosa.herokuapp.com")
	@PostMapping
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioDto usuarioDto,UriComponentsBuilder uriBuilder){
		
		Usuario usuario = usuarioDto.converterUsuario();
		usuario.setSenha(BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt(12)));
		usuarioRepository.save(usuario);
		
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}
	
	@GetMapping("/{id}")
	public UsuarioDto detalhar (@PathVariable Long id) {
		
		Usuario usuario = usuarioRepository.getOne(id);
		
		return new UsuarioDto(usuario);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar (@PathVariable Long id,@RequestBody @Valid UsuarioDto usuariodto){
		
		Usuario usuario = usuariodto.atualizar(id, usuarioRepository);
		
		return ResponseEntity.ok(new UsuarioDto(usuario));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar (@PathVariable Long id){
		
		usuarioRepository.deleteById(id);
		
		return ResponseEntity.ok("Resgistro deletado com sucesso");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
