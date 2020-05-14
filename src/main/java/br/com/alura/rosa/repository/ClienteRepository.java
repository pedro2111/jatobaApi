package br.com.alura.rosa.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.rosa.modelo.Cliente;
import br.com.alura.rosa.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByOrderByNomeAsc();


}
