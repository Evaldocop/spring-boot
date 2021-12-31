package com.evaldo.testeajax;

import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = "classpath:dwr-spring.xml")
@SpringBootApplication
public class TesteAjaxApplication  implements CommandLineRunner  {

	public static void main(String[] args) {

		SpringApplication.run(TesteAjaxApplication.class, args);
	}

	
	 // @Autowired private SocialMetaTagService service;
	 
	
	  @Override public void run(String... args) throws Exception {

	  
	  }
	  @Bean
	  public ServletRegistrationBean<DwrSpringServlet> dwrSpringServlet(){
		  DwrSpringServlet dwrSpringServlet = new DwrSpringServlet();
		  ServletRegistrationBean< DwrSpringServlet> registrationBean=
				  new ServletRegistrationBean<>(dwrSpringServlet,"/dwr/*");
		  registrationBean.addInitParameter("debug", "true");
		  registrationBean.addInitParameter("activeReverseAjaxEnabled",  "true");
		  return registrationBean;
		  
	  }
	

}
