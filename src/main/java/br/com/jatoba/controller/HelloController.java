package br.com.jatoba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.jatoba.repository.ProdutoRepository;

@Controller
public class HelloController {
	
	@RequestMapping("/")
	@ResponseBody
	public String ola() {
		
		return "Ola mundo!!";
		
	}


}
