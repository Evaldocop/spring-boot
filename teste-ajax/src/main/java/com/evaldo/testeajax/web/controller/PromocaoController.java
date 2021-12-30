package com.evaldo.testeajax.web.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
import com.evaldo.testeajax.service.PromocaoDataTablesService;

import dto.PromocaoDTO;

@Controller
@RequestMapping("/promocao")
public class PromocaoController {
	
	private static Logger logger = LoggerFactory.getLogger(PromocaoController.class);
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private PromocaoRepository promocaoRepository;
	
	//================EDITAR PROMOCAO ==========================================
	
	        @PostMapping("/edit")
			public ResponseEntity<?> editarPromocao(@Valid PromocaoDTO dto, BindingResult result){
				
				if(result.hasErrors()) {
					Map<String,String> errors=new HashMap<>();
					for(FieldError error :result.getFieldErrors()) {
						errors.put(error.getField(), error.getDefaultMessage());
					}
					
					return ResponseEntity.unprocessableEntity().body(errors);
					
				}
				Promocao promo = promocaoRepository.findById(dto.getId()).get();
				promo.setCategoria(dto.getCategoria());
				promo.setDescricao(dto.getDescricao());
				promo.setLinkImagem(dto.getLinkImagem());
				promo.setPreco(dto.getPreco());
				promo.setTitulo(dto.getTitulo());
				promocaoRepository.save(promo);
				return ResponseEntity.ok().build();
		    }
		
	
	
	//================ PRE EDITAR PROMOCAO ==========================================
	
		@GetMapping("/edit/{id}")
		public ResponseEntity<?> preEditarPromocao(@PathVariable ("id") Long id){
			Promocao promo = promocaoRepository.findById(id).get();
			return ResponseEntity.ok(promo);
	    }
		
	//================ EXCLUSAO PROMOCAO ==========================================
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<?> excluirPromocao(@PathVariable ("id") Long id){
		promocaoRepository.deleteById(id);
		return ResponseEntity.ok().build();
    }
	
	//================ DATATABLES =================================================
	
	@GetMapping("/tabela")
    public String showTabela() {
		return "promo-datatables";
    	
    }
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> datatables(HttpServletRequest request){
		Map<String, Object> data = new PromocaoDataTablesService().execute(promocaoRepository, request);
		return ResponseEntity.ok(data);
	}
	
	
	
	
	//================ DATATABLES =================================================
		
	
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
	  //ordenamento não funciona
    	Sort.Direction sort= Sort.Direction.ASC;
    //	 Sort sort= Sort.by(Direction.ASC,"dtCadastro");
	   PageRequest pagesRequest = PageRequest.of(0,5 , sort);
		model.addAttribute("promocoes",promocaoRepository.findAll(pagesRequest));
		return "promo-list";
	}
	
	
	@GetMapping("/list/ajax")
	public String listarCards(@RequestParam(name = "page" ,defaultValue = "1") int page,
			                  @RequestParam(name = "site", defaultValue = "") String site,
			                  ModelMap model){
	
	   Sort sort= Sort.by(Direction.ASC,"dtCadastro");
	   PageRequest pagesRequest = PageRequest.of(page, 3, sort);
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
		   PageRequest pagesRequest = PageRequest.of(0, 3, sort);
		   model.addAttribute("promocoes",promocaoRepository.findBySite(site, pagesRequest));
			return "promo-card";
	}

}
