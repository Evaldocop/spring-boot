package com.evaldo.testeajax.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evaldo.testeajax.domain.Categoria;
import com.evaldo.testeajax.domain.Promocao;
import com.evaldo.testeajax.repository.CategoriaRepository;
import com.evaldo.testeajax.repository.PromocaoRepository;

@Controller
@RequestMapping("/promocao")
public class PromocaoController {
	
	private static Logger logger = LoggerFactory.getLogger(PromocaoController.class);
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private PromocaoRepository promocaoRepository;
	
	
	@ModelAttribute("categorias")
	public List<Categoria> getCategorias(){
		return categoriaRepository.findAll();
	};

	@GetMapping("/add")
	public String abrirCadastro() {
		return "/promo-add";
	}
	
	
	@PostMapping("/save")
	public ResponseEntity< Promocao> savRPromocao(Promocao promo){
		
		
		logger.info("Promocao{}", promo.toString());
		promo.setDtCadastro(LocalDateTime.now());
		promocaoRepository.save(promo);
		return ResponseEntity.ok().build();
		
	}

}
