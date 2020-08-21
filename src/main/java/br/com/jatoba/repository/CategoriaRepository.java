package br.com.jatoba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jatoba.modelo.Categoria;



public interface CategoriaRepository  extends JpaRepository<Categoria, Long>{
	
	@Query("SELECT c FROM Categoria c order by c.nome asc")
	List<Categoria> listarPorNome();

	Categoria findByNome(String nomeCategoria);

}
