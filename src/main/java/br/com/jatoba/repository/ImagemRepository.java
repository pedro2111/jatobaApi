package br.com.jatoba.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jatoba.modelo.Imagem;
import br.com.jatoba.modelo.Produto;

public interface ImagemRepository  extends JpaRepository<Imagem, Long>{
	
	List<Imagem> findByOrderById();

	@Query("SELECT img FROM Imagem img where img.tipo = 'CAPA' ORDER BY id DESC")
	List<Imagem> findCapa();

	@Query("SELECT img FROM Imagem img where img.tipo = 'CAPA' ORDER BY id DESC")
	Page<Imagem> findCapaPaginacao(Pageable paginacao);

	@Query("SELECT i FROM Imagem i WHERE i.produto = :produto ORDER BY i.id DESC")
	List<Imagem> findByProduto(@Param("produto") Produto produto);

	@Query("DELETE from Imagem i where i.produto = :produto")
	void deleteByProduto(@Param("produto") Produto produto);

}
