package br.com.alura.rosa.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.rosa.modelo.Topico;
import br.com.alura.rosa.repository.TopicoRepository;

public class AtualizarTopicoFormDto {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotNull @NotEmpty
	
	private String mensagem;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		
		Topico topico = topicoRepository.getOne(id);
		topico.setTitulo(titulo);
		topico.setMensagem(mensagem);
				
		return topico;
	} 
	
	

}
