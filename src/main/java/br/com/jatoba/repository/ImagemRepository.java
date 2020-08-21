package br.com.jatoba.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jatoba.modelo.Imagem;

public interface ImagemRepository  extends JpaRepository<Imagem, Long>{
	
	List<Imagem> findByOrderById();

	@Query("SELECT img FROM Imagem img where img.tipo = 'CAPA' ORDER BY id DESC")
	List<Imagem> findCapa();

	@Query("SELECT img FROM Imagem img where img.tipo = 'CAPA' ORDER BY id DESC")
	Page<Imagem> findCapaPaginacao(Pageable paginacao);

}