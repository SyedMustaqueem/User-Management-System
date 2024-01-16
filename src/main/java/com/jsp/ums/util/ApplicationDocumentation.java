package com.jsp.ums.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;



@Configuration
@OpenAPIDefinition
public class ApplicationDocumentation {

	Contact contact() {
		return new Contact().email("Brezzas.com").url("www.kingKhan.com").name("Legendary King");
	}
	
	Info info() {
		return new Info().title("User Management System API")
				.version("1.0v")
				.description("User Management System is a restful API built using "
						+ "Spring Boot And MySql DataBase")
				.contact( contact());
	}


	@Bean
	OpenAPI openAPI() {
		return new OpenAPI()

				.info(info());
	}
}


