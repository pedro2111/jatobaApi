package br.com.jatoba.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Orcamento {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String email;
	private String telefone;
	private String assunto;
	private String mensagem;
	private LocalDate dtCriacao = LocalDate.now();
	
	@ManyToMany
	private List<Imagem> imagens = new ArrayList<>();
	
	public Orcamento () {
		
	}
	

	public Orcamento(String nome, String email, String telefone, String assunto, String mensagem) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.assunto = assunto;
		this.mensagem = mensagem;
	}
	


	public Orcamento(String nome, String email, String telefone, String assunto, String mensagem, List<Imagem> imagens) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.assunto = assunto;
		this.mensagem = mensagem;
		this.imagens = imagens;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDate getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(LocalDate dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}
	

}
