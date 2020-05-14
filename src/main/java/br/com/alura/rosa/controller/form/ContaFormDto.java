package br.com.alura.rosa.controller.form;

import java.time.LocalDate;

import br.com.alura.rosa.modelo.Cliente;
import br.com.alura.rosa.modelo.Conta;
import br.com.alura.rosa.repository.ClienteRepository;

public class ContaFormDto {
	
	private Long cliente_id;
	private LocalDate dt_abertura;
	
	public Long getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}
	public LocalDate getdt_abertura() {
		return dt_abertura;
	}
	public void setdt_abertura(LocalDate dt_abertura) {
		this.dt_abertura = dt_abertura;
	}

	
	public ContaFormDto() {
		
	}
	
	
	public Conta converter(ClienteRepository clienteRepo ) {
		
		Cliente cliente = clienteRepo.findById(this.cliente_id).orElse(null);
		
		return new Conta(cliente);
		
	}
	
	

}
