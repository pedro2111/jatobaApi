package br.com.jatoba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jatoba.modelo.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

	List<Orcamento> findByOrderById();

}
