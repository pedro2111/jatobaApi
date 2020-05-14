package br.com.alura.rosa.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.rosa.modelo.Boleto;
import br.com.alura.rosa.modelo.Conta;

public class ContaDto {
	
	private Long id;
	private String nomeCliente;
	private LocalDate dt_abertura;
	private LocalDate dt_fechamento;
	
	public ContaDto(Conta conta) {
		this.id = conta.getId();
		this.nomeCliente = conta.getCliente().getNome();
		this.dt_abertura = conta.getDt_abertura();
		this.dt_fechamento = conta.getDt_fechamento();
		
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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
	
	public static List<ContaDto> converter(List<Conta> contas){
		
		return contas.stream().map(ContaDto::new).collect(Collectors.toList());
	}
	
	

}
