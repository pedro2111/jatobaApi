package br.com.alura.rosa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.rosa.modelo.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	
}
