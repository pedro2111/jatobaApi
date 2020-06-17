package br.com.alura.rosa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.rosa.modelo.Cliente;
import br.com.alura.rosa.modelo.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	@Query(value = "SELECT c from Conta c where c.cliente = :cliente and c.dt_fechamento is not null ORDER BY c.id DESC")
    List<Conta> findContaByCliente(@Param("cliente") Cliente cliente);

	
}
