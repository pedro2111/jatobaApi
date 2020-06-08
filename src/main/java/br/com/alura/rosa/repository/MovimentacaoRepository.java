package br.com.alura.rosa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.rosa.modelo.Cliente;
import br.com.alura.rosa.modelo.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{

	//--Todos clientes com conta aberta e com movimentacao
	@Query(value = "SELECT c.nome,c.id, sum(valor),m.conta_id\n" + 
			"			FROM public.movimentacao m \n" + 
			"			left join public.cliente c on c.id = m.cliente_id\n" + 
			"			left join public.conta co on co.id = m.conta_id\n" + 
			"			where co.dt_fechamento is null\n" + 
			"			group by c.id,m.cliente_id,m.conta_id",
			nativeQuery = true)
	List<?> findMovimentacoesPorCliente();

	@Query(value = "SELECT c.id, sum(valor)\n" + 
			"			FROM public.movimentacao m right join public.cliente c on c.id = m.cliente_id\n" + 
			"			left join public.conta co on co.id = m.conta_id\n" + 
			"			where co.dt_fechamento is null\n" + 
			"			group by c.id,m.cliente_id",
			nativeQuery = true)
	List<?> findTotalClienteMovimentacao();
	
	//Todos clientes com conta fechada sem conta aberta
	@Query(value = "SELECT distinct on (c.nome) c.nome,c.id, co.id as conta_id\n" + 
			"			FROM public.cliente c\n" + 
			"			left join public.conta co on co.cliente_id = c.id\n" + 
			"			where co.dt_fechamento is not null\n" + 
			"			and c.id not in (SELECT c.id\n" + 
			"			FROM public.movimentacao m \n" + 
			"			left join public.cliente c on c.id = m.cliente_id\n" + 
			"			left join public.conta co on co.id = m.conta_id\n" + 
			"			where co.dt_fechamento is null)\n" + 
			"			and c.id not in (SELECT c.id\n" + 
			"			FROM public.cliente c\n" + 
			"			left join public.conta co on co.cliente_id = c.id\n" + 
			"			where c.id not in(select m.cliente_id from movimentacao m left join public.conta co on co.id = m.conta_id where co.dt_fechamento is null)\n" + 
			"			and co.dt_fechamento is null)",
			nativeQuery = true)
	List<?> findClienteSemMovimentacao();
	
	//--clientes novos e clientes com conta e sem movimentacao
	@Query(value = 	"SELECT c.nome,c.id, co.id as conta_id\n" + 
			"			FROM public.cliente c\n" + 
			"			left join public.conta co on co.cliente_id = c.id\n" + 
			"			where c.id not in(select m.cliente_id from movimentacao m left join public.conta co on co.id = m.conta_id where co.dt_fechamento is null)\n" + 
			"			and co.dt_fechamento is null",
			nativeQuery = true)
	List<?> findClienteContaSemMovimentcao();

	
	
	/*
	 * @Query(value = "SELECT distinct on (c.nome) c.nome, m.id,dt_movimentacao,observacao, tipo, valor, cliente_id, conta_id " + 
			"FROM public.movimentacao m join public.cliente c on c.id = m.cliente_id " + 
			"order by c.nome, m.id desc",
			nativeQuery = true)
			
			
			@Query("SELECT DISTINCT m FROM Movimentacao m LEFT JOIN m.cliente c ORDER BY m.id DESC")
			
			@Query(value = "SELECT new br.com.alura.rosa.controller.dto.MovimentacaoDto(distinct on (c.nome) c.nome, m.id, " +
			"dt_movimentacao, observacao, tipo, valor, cliente_id, conta_id) " +
			"FROM public.movimentacao m join public.cliente c on c.id = m.cliente_id " +
			"order by c.nome, m.id desc",
			nativeQuery = true)
	List<Movimentacao> findIlderlan();
			
	 * */

}
