package br.com.alura.rosa.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.rosa.modelo.Movimentacao;
import br.com.alura.rosa.modelo.TipoMovimentacao;

public class MovimentacaoDto {

	private String nome;
	private Long id;
	private LocalDateTime dt_movimentacao;
	private String observacao;
	private TipoMovimentacao tipo;
	private BigDecimal valor;
	private Long cliente_id;
	private Long conta_id;

	public MovimentacaoDto(Movimentacao mov) {

		this.nome = mov.getCliente().getNome();
		this.id = mov.getId();
		this.dt_movimentacao = mov.getDt_movimentacao();
		this.observacao = mov.getObservacao();
		this.tipo = mov.getTipo();
		this.valor = mov.getValor();
		this.cliente_id = mov.getCliente().getId();
		this.conta_id = mov.getConta().getId();
	}
	
	public MovimentacaoDto(String nome, Long id, LocalDateTime dt_movimentacao, String observacao, TipoMovimentacao tipo, BigDecimal valor, Long cliente_id, Long conta_id) {
		this.nome = nome;
		this.id = id;
		this.dt_movimentacao = dt_movimentacao; 
		this.observacao = observacao;
		this.tipo = tipo;
		this.valor = valor;
		this.cliente_id = cliente_id;
	
	}
	
	// distinct on (c.nome) c.nome, m.id,dt_movimentacao,observacao, tipo, valor,
	// cliente_id, conta_id

	public static List<MovimentacaoDto> converter(List<Movimentacao> mov) {

		return mov.stream().map(MovimentacaoDto::new).collect(Collectors.toList());

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDt_movimentacao() {
		return dt_movimentacao;
	}

	public void setDt_movimentacao(LocalDateTime dt_movimentacao) {
		this.dt_movimentacao = dt_movimentacao;
	}

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

}
