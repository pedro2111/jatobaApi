package br.com.alura.rosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.rosa.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String nomeCurso);
	
	

}
