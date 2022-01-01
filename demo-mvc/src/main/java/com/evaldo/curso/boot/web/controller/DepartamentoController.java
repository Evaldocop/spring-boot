package com.evaldo.curso.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@GetMapping("/cadastrar")
	public String Cadastro() {
		return "/departamento/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/departamento/lista";
	}

}
