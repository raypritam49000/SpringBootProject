package com.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employees API", version = "3.0", description = "Employees Information"))
public class SpringBootWithSwegger3DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithSwegger3DemoApplication.class, args);
	}

}
