package br.com.alura.rosa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.rosa.modelo.Boleto;


public interface BoletoRepository extends JpaRepository<Boleto, Long>{

	List<Boleto> findByOrderByIdDesc();

	@Query("SELECT b from Boleto b "
			+ "where date_part('day',age(b.dtVencimento, date(now()))) <=7 "
			+ "and date_part('day',age(dt_vencimento, date(now()))) >=1 "
			+ "and b.status <> 'PAGO' "
			+ "order by b.dtVencimento asc")
	List<Boleto> findAVencer();

	@Query("SELECT b from Boleto b where date_part('day',age(b.dtVencimento, date(now()))) < 0 and b.status <> 'PAGO' order by b.dtVencimento asc")
	List<Boleto> findVencidos();
	
	@Query("SELECT b from Boleto b where date_part('day',age(b.dtVencimento, date(now()))) = 0 and b.status <> 'PAGO' order by b.dtVencimento asc")
	List<Boleto> findVencidosHoje();

	@Query("SELECT count(*) from Boleto b where date_part('day',age(b.dtVencimento, date(now()))) = 0 and b.status <> 'PAGO' ")
	int countVencidoHoje();

	@Query("SELECT count(*) from Boleto b where date_part('day',age(b.dtVencimento, date(now()))) < 0 and b.status <> 'PAGO' ")
	int countVencido();

	@Query("SELECT count(*) from Boleto b "
			+ "where date_part('day',age(b.dtVencimento, date(now()))) <=7 "
			+ "and date_part('day',age(dt_vencimento, date(now()))) >=1 "
			+ "and b.status <> 'PAGO' ")
	int countAVencer();

	@Query("SELECT count(*) from Boleto b where b.status = 'PAGO' ")
	int countPago();

	@Query("select extract(month from b.dtVencimento) as mes,\n" + 
			"       extract(year from b.dtVencimento) as ano,\n" + 
			"       sum(b.valor) as Total\n" + 
			"from Boleto b\n" + 
			"group by 1,2" +
			"order by 1,2")
	List countTotalMes();

	

	

}
