package br.com.jatoba.formDto;

import java.util.ArrayList;
import java.util.List;

import br.com.jatoba.modelo.Orcamento;
import br.com.jatoba.modelo.Produto;
import br.com.jatoba.repository.ProdutoRepository;

public class OrcamentoFormDto {
	
	private String nome;
	private String email;
	private String telefone;
	private String assunto;
	private String mensagem;
	private List<Long> produto_ids = new ArrayList<>();
	
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
	public List<Long> getProduto_ids() {
		return produto_ids;
	}
	public void setProduto_ids(List<Long> produto_ids) {
		this.produto_ids = produto_ids;
	}
	
	public Orcamento convert(ProdutoRepository produtoRepo) {
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		produto_ids.forEach(produto_id -> {
			
			Produto produto = produtoRepo.getOne(produto_id);
			
			produtos.add(produto);
			
		});
		
		return new Orcamento(nome, email, telefone, assunto, mensagem, produtos);
	}
	
	

	
	

}
