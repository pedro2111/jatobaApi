package br.com.alura.rosa.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.rosa.modelo.Boleto;
import br.com.alura.rosa.modelo.StatusBoleto;

public class BoletoDto {

	private Long id;
	private String nome;
	private LocalDate dtCriacao;
	private String nomeCategoria;
	private String nomeUsuario;
	private String nomeFornecedor;
	private StatusBoleto status;
	private LocalDate dtVencimento;
	private BigDecimal valor;


	public BoletoDto(Boleto boleto) {
		this.id = boleto.getId();
		this.nome = boleto.getNome();
		this.dtCriacao = boleto.getDtCriacao();
		this.nomeCategoria = boleto.getCategoria().getNome();
		this.nomeUsuario = boleto.getUsuario().getNome();
		this.nomeFornecedor = boleto.getFornecedor().getNome();
		this.status = boleto.getStatus();
		this.dtVencimento = boleto.getDtVencimento();
		this.valor = boleto.getValor();
		
	}


	public BigDecimal getValor() {
		return valor;
	}


	public LocalDate getDtVencimento() {
		return dtVencimento;
	}
	
	public StatusBoleto getStatus() {
		return status;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDtCriacao() {
		return dtCriacao;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public static List<BoletoDto> converter(List<Boleto> boletos) {
		
		return boletos.stream().map(BoletoDto::new).collect(Collectors.toList());
		
	}
	
	
}
