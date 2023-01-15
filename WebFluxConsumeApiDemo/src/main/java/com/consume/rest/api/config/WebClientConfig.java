package com.consume.rest.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient webClient() {
		WebClient client = WebClient.create("https://jsonplaceholder.typicode.com/todos");
		return client;
	}

}
