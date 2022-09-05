package com.ray.pritam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.ray.pritam.services.ConsumeApiService;

@Configuration
public class AppConfig {

	@Bean
	public ConsumeApiService chuckNorrisClient() throws Exception {
		WebClient webClient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
		HttpServiceProxyFactory factory = WebClientAdapter.createHttpServiceProxyFactory(webClient);
		factory.afterPropertiesSet();
		return factory.createClient(ConsumeApiService.class);
	}
}