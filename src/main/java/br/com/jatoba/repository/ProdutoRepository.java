package br.com.jatoba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jatoba.modelo.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	List<Produto> findByOrderByIdDesc();

	@Query("SELECT p,i FROM Produto p JOIN p.imagens i ORDER BY p.id DESC")
	List<?> findProdutoImagens();
	

}
