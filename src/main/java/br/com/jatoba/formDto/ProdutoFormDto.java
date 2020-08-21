package br.com.jatoba.formDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.jatoba.modelo.Categoria;
import br.com.jatoba.modelo.Imagem;
import br.com.jatoba.modelo.Produto;
import br.com.jatoba.repository.CategoriaRepository;

public class ProdutoFormDto {
	
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private BigDecimal altura;
	private BigDecimal largura;
	private BigDecimal profundidade;
	private BigDecimal peso;
	private String nomeCategoria;
	private List<Imagem> imagens = new ArrayList<>();
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public List<Imagem> getImagens() {
		return imagens;
	}
	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}
	
	
	public Produto converter(CategoriaRepository categoriaRepo) {
		
		Categoria categoria = categoriaRepo.findByNome(nomeCategoria);
		
		return new Produto(nome,descricao,preco, altura, largura, profundidade, peso, categoria);
	}
	
	

}
