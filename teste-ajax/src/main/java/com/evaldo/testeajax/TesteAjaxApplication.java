package com.evaldo.testeajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.evaldo.testeajax.service.SocialMetaTagService;

@SpringBootApplication
public class TesteAjaxApplication  implements CommandLineRunner  {

	public static void main(String[] args) {

		SpringApplication.run(TesteAjaxApplication.class, args);
	}

	
	  @Autowired private SocialMetaTagService service;
	 
	
	  @Override public void run(String... args) throws Exception {
	  
			/*
			 * SocialMetaTag tag = service.getSocialMetaTagbyUrl(
			 * "https://www.udemy.com/course/spring-boot-mvc-com-thymeleaf/");
			 * System.out.println(tag.toString());
			 */
	  
	  }
	 

}
