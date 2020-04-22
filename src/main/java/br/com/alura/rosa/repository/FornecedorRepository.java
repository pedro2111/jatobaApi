package br.com.alura.rosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.rosa.modelo.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

	Fornecedor findByNome(String nomeFornecedor);

}
