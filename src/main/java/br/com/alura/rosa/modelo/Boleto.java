package br.com.alura.rosa.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

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
public class Boleto {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private LocalDate dtCriacao = LocalDate.now();
	private LocalDate dtVencimento;
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private StatusBoleto status = StatusBoleto.NAO_PAGO;
	
	@ManyToOne
	private Categoria categoria;

	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Fornecedor fornecedor;
	
	public Boleto() {
		
	}
	

	public Boleto(String nome, LocalDate dtCriacao, Categoria categoria, Usuario usuario, Fornecedor fornecedor,StatusBoleto status, LocalDate dtVencimento,BigDecimal valor) {
		this.nome = nome;
		this.dtCriacao = dtCriacao;
		this.categoria = categoria;
		this.usuario = usuario;
		this.fornecedor = fornecedor;
		this.status = status;
		this.dtVencimento = dtVencimento;
		this.valor = valor;
	}
	
	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public StatusBoleto getStatus() {
		return status;
	}


	public void setStatus(StatusBoleto status) {
		this.status = status;
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

	public LocalDate getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(LocalDate dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}


	public LocalDate getDtVencimento() {
		return dtVencimento;
	}


	public void setDtVencimento(LocalDate dtVencimento) {
		this.dtVencimento = dtVencimento;
	}
	
	
}
