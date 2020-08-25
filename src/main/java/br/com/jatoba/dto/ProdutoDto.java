package br.com.jatoba.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.jatoba.modelo.Produto;

public class ProdutoDto {
	
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private BigDecimal altura;
	private BigDecimal largura;
	private BigDecimal profundidade;
	private BigDecimal peso;
	private String nomeCategoria;
	private LocalDate dtCriacao;
	
	
	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.altura = produto.getAltura();
		this.largura = produto.getLargura();
		this.profundidade = produto.getProfundidade();
		this.peso = produto.getPreco();
		this.nomeCategoria = produto.getCategoria().getNome();
		this.dtCriacao = produto.getDtCriacao();
	}
	
	
	public LocalDate getDtCriacao() {
		return dtCriacao;
	}


	public void setDtCriacao(LocalDate dtCriacao) {
		this.dtCriacao = dtCriacao;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public BigDecimal getAltura() {
		return altura;
	}
	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}
	public BigDecimal getLargura() {
		return largura;
	}
	public void setLargura(BigDecimal largura) {
		this.largura = largura;
	}
	public BigDecimal getProfundidade() {
		return profundidade;
	}
	public void setProfundidade(BigDecimal profundidade) {
		this.profundidade = profundidade;
	}
	public BigDecimal getPeso() {
		return peso;
	}
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	public static ProdutoDto converter(Produto produto) {
		
		return new ProdutoDto(produto);
	}
	
	

}
