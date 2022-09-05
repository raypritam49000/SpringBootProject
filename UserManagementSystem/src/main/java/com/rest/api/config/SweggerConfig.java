package com.rest.api.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SweggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).paths(Predicates.not(PathSelectors.regex("/error.*"))).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("EMPLOYEE MANAGMENT SYSTEM REST API", "employee management system using spring boot", "1.0",
				"Terms of service",
				new Contact("Pritam Ray", "http://www.androjavatech4u.com", "raypritam49000@gmail.com"),
				"License of API", "API license URL", Collections.emptyList());
	}

}