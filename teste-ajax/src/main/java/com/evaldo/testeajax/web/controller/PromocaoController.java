package com.evaldo.testeajax.web.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	public ResponseEntity<?> savRPromocao(@Valid Promocao promo, BindingResult result){
		
		if(result.hasErrors()) {
			Map<String,String> errors=new HashMap<>();
			for(FieldError error :result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			
			return ResponseEntity.unprocessableEntity().body(errors);
			
		}
			
		logger.info("Promocao{}", promo.toString());
		promo.setDtCadastro(LocalDateTime.now());
		promocaoRepository.save(promo);
		return ResponseEntity.ok().build();
		
	}

}
