package br.com.alura.rosa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.rosa.modelo.Cliente;
import br.com.alura.rosa.modelo.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{

	@Query(value = "SELECT distinct on (c.nome) c.nome, m.id,dt_movimentacao,observacao, tipo, valor, cliente_id, conta_id " + 
			"FROM public.movimentacao m join public.cliente c on c.id = m.cliente_id " + 
			"order by c.nome, m.id desc",
			nativeQuery = true)
	List<?> findMovimentacoesPorCliente();

	List<Movimentacao> findFirstByOrderById();
	
	/*
	 * @Query(value = "SELECT distinct on (c.nome) c.nome, m.id,dt_movimentacao,observacao, tipo, valor, cliente_id, conta_id " + 
			"FROM public.movimentacao m join public.cliente c on c.id = m.cliente_id " + 
			"order by c.nome, m.id desc",
			nativeQuery = true)
			@Query("SELECT DISTINCT m FROM Movimentacao m LEFT JOIN m.cliente c ORDER BY m.id DESC")
	 * */

}
