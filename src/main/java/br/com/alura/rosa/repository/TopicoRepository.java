package br.com.alura.rosa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.rosa.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

	Page<Topico> findByCursoNome(String nome, Pageable paginacao);


	
	//A titulo de exemplo
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nome")
	List<Topico> listarPorNomeCurso(@Param("nome") String nome);
	
	

}
