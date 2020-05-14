package br.com.alura.rosa.controller.form;

import java.math.BigDecimal;

import br.com.alura.rosa.modelo.Cliente;
import br.com.alura.rosa.modelo.Conta;
import br.com.alura.rosa.modelo.Movimentacao;
import br.com.alura.rosa.modelo.TipoMovimentacao;
import br.com.alura.rosa.repository.ClienteRepository;
import br.com.alura.rosa.repository.ContaRepository;

public class MovimentacaoFormDto {
	
	private String observacao;
	private TipoMovimentacao tipo;
	private BigDecimal valor;
	private Long cliente_id;
	private Long conta_id;
	
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public TipoMovimentacao getTipo() {
		return tipo;
	}
	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Long getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}
	public Long getConta_id() {
		return conta_id;
	}
	public void setConta_id(Long conta_id) {
		this.conta_id = conta_id;
	}
	
	public Movimentacao converter (ClienteRepository cliRepo, ContaRepository contaRepo ) {
		
		Cliente cliente = cliRepo.getOne(cliente_id);
		Conta conta = contaRepo.getOne(conta_id);
		
		return new Movimentacao(valor, observacao, tipo, cliente, conta);		
	}
	

}
