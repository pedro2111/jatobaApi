package br.com.jatoba.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movimentacao {
	
	//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal valor;
	private String observacao;
	private LocalDateTime dt_movimentacao = LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Conta conta;
	
	public Movimentacao() {
		
	}
	
	

	public Movimentacao(BigDecimal valor, String observacao, TipoMovimentacao tipo, Cliente cliente, Conta conta) {
		this.valor = valor;
		this.observacao = observacao;
		this.tipo = tipo;
		this.cliente = cliente;
		this.conta = conta;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public LocalDateTime getDt_movimentacao() {
		return dt_movimentacao;
	}

	public void setDt_movimentacao(LocalDateTime dt_movimentacao) {
		this.dt_movimentacao = dt_movimentacao;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	
	

}
