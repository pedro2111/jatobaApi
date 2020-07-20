package br.com.jatoba.modelo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Conta {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Cliente cliente;
	
	private LocalDate dt_abertura = LocalDate.now();;
	private LocalDate dt_fechamento;
	
	
	public Conta () {
		
	}
	
	public Conta(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public LocalDate getDt_abertura() {
		return dt_abertura;
	}
	public void setDt_abertura(LocalDate dt_abertura) {
		this.dt_abertura = dt_abertura;
	}
	public LocalDate getDt_fechamento() {
		return dt_fechamento;
	}
	public void setDt_fechamento(LocalDate dt_fechamento) {
		this.dt_fechamento = dt_fechamento;
	}
	
	
	
	

}
