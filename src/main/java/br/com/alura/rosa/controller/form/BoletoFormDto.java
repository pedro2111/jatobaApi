package br.com.alura.rosa.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.rosa.modelo.Boleto;
import br.com.alura.rosa.modelo.Categoria;
import br.com.alura.rosa.modelo.Fornecedor;
import br.com.alura.rosa.modelo.StatusBoleto;
import br.com.alura.rosa.modelo.Usuario;
import br.com.alura.rosa.repository.BoletoRepository;
import br.com.alura.rosa.repository.CategoriaRepository;
import br.com.alura.rosa.repository.FornecedorRepository;
import br.com.alura.rosa.repository.UsuarioRepository;

public class BoletoFormDto {

	private String nome;
	private LocalDate dtCriacao;
	private String nomeCategoria;
	private String nomeUsuario;
	private String nomeFornecedor;
	private StatusBoleto status = StatusBoleto.NAO_PAGO;
	private LocalDate dtVencimento;
	private BigDecimal valor;
	
	
	
	public void setStatus(StatusBoleto status) {
		this.status = status;
	}

	public void setDtVencimento(LocalDate dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
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
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	
	public Boleto converter(UsuarioRepository usuarioRepository,FornecedorRepository fornecedorRepository,CategoriaRepository categoriaRepository) {
		
		Usuario usuario = usuarioRepository.listarPorNomeUsuario(nomeUsuario);
		
		Categoria categoria = categoriaRepository.findByNome(nomeCategoria);
		
		Fornecedor fornecedor = fornecedorRepository.findByNome(nomeFornecedor);
		
		
		return new Boleto(nome, dtCriacao, categoria, usuario, fornecedor, status, dtVencimento, valor);
	}
	public Boleto atualizar(Long id, BoletoRepository boletoRepository) {
		
		Boleto boleto = boletoRepository.getOne(id);
		boleto.setNome(nome);
		boleto.setDtCriacao(dtCriacao);
		boleto.getFornecedor().setNome(nomeFornecedor);
		boleto.getCategoria().setNome(nomeCategoria);
		boleto.setStatus(status);
		boleto.setDtVencimento(dtVencimento);
		boleto.setValor(valor);
		
		return boleto;
	}
	
	
}
