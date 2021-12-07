package com.evaldo.testeajax.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.evaldo.testeajax.domain.SocialMetaTag;
import com.evaldo.testeajax.service.SocialMetaTagService;

@Controller
@RequestMapping("/meta")
public class SocialMetaTagController {
	
	@Autowired
	private SocialMetaTagService socialMetaTagService;
	@PostMapping("/info")
	ResponseEntity<SocialMetaTag> getDadosViaUrl(@RequestParam("url") String url){
		
		SocialMetaTag socialMetaTag = socialMetaTagService.getSocialMetaTagbyUrl(url);
		return socialMetaTag!=null ? ResponseEntity.ok(socialMetaTag) : ResponseEntity.notFound().build();
	}

	public SocialMetaTagController() {
		// TODO Auto-generated constructor stub
	}

}
