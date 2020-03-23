package br.com.alura.rosa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.rosa.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	List<Usuario> findByNome(String nome);
	
	Optional<Usuario> findByEmail(String email);
	
	

}
