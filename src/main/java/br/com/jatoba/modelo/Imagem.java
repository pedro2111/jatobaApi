package br.com.jatoba.modelo;

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
public class Imagem {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String public_id;
	private String url;
	
	@Enumerated(EnumType.STRING)
	private TipoImagem tipo;
	
	@ManyToOne
	private Produto produto;
	
	public Imagem () {
	
	}

	

	public Imagem(String nome, String public_id, String url, TipoImagem tipo, Produto produto) {
		this.nome = nome;
		this.public_id = public_id;
		this.url = url;
		this.tipo = tipo;
		this.produto = produto;

	}
	

	
	public TipoImagem getTipo() {
		return tipo;
	}

	public void setTipo(TipoImagem tipo) {
		this.tipo = tipo;
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

	public String getPublic_id() {
		return public_id;
	}

	public void setPublic_id(String public_id) {
		this.public_id = public_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	
	

}
