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

	@Query("SELECT img FROM Imagem img where img.tipo = 'CAPA' and img.produto.categoria.id in :ids ORDER BY id DESC")
	Page<Imagem> findCapaPaginacao(Pageable paginacao,@Param("ids") List<Long> categoria);

	@Query("SELECT i FROM Imagem i WHERE i.produto = :produto ORDER BY i.id DESC")
	List<Imagem> findByProduto(@Param("produto") Produto produto);
	
	@Query("SELECT i FROM Imagem i WHERE i.produto = :produto and i.tipo = 'CAPA' ORDER BY i.id DESC")
	Imagem findByProdutoCapa(@Param("produto") Produto produto);

	@Query("DELETE from Imagem i where i.produto = :produto")
	void deleteByProduto(@Param("produto") Produto produto);

	@Query("SELECT i FROM Imagem i where i.tipo = 'CAPA' ORDER BY i.produto.curtidas DESC")
	Page<Imagem> findMainProdutos(Pageable paginacao);

	@Query("SELECT i FROM Imagem i where i.tipo = 'CAPA' ORDER BY i.produto.id DESC")
	Page<Imagem> findMainUltimosProdutos(Pageable paginacao);

}
