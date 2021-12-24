package com.evaldo.testeajax.web.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	@GetMapping("/list")
	public String listarOfertas(ModelMap model){
	  //ordenamenti não funciona
	   Sort sort= Sort.by(Direction.ASC,"dtCadastro");
	   PageRequest pagesRequest = PageRequest.of(0, 2, sort);
		model.addAttribute("promocoes",promocaoRepository.findAll(pagesRequest));
		return "promo-list";
	}
	
	
	@GetMapping("/list/ajax")
	public String listarCards(@RequestParam(name = "page" ,defaultValue = "1") int page,
			                  @RequestParam(name = "site", defaultValue = "") String site,
			                  ModelMap model){
	
	   Sort sort= Sort.by(Direction.ASC,"dtCadastro");
	   PageRequest pagesRequest = PageRequest.of(page, 2, sort);
	   if (site.isEmpty()) {
			model.addAttribute("promocoes", promocaoRepository.findAll(pagesRequest));
		} else {
			model.addAttribute("promocoes", promocaoRepository.findBySite(site, pagesRequest));
		}
		return "promo-card";
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
	
	@PostMapping("/like/{id}")
	public ResponseEntity<?> adicionarLikes(@PathVariable ("id") Long id){
		
		promocaoRepository.updateSomarLikes(id);
		int likes = promocaoRepository.findLikesById(id);
		
		
		return ResponseEntity.ok(likes);
		
	}
	// ======================================AUTOCOMPLETE===============================================
	@GetMapping("/site")
	public ResponseEntity<?>  autocompleteByTermo(@RequestParam("termo") String site){
		List<String> sites= promocaoRepository.findSiteTermo(site);
		return ResponseEntity.ok(sites);
	}
	
	@GetMapping("/site/list")
	public String listarPorSite(@RequestParam("site") String site,ModelMap model) {
		 
		 Sort sort= Sort.by(Direction.ASC,"dtCadastro");
		   PageRequest pagesRequest = PageRequest.of(0, 2, sort);
		   model.addAttribute("promocoes",promocaoRepository.findBySite(site, pagesRequest));
			return "promo-card";
	}

}
