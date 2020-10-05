package br.com.jatoba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jatoba.modelo.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

	List<Orcamento> findTop50ByOrderByIdDesc();

}
