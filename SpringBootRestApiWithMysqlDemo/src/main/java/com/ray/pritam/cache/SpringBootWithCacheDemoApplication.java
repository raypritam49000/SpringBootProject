package com.ray.pritam.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootWithCacheDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithCacheDemoApplication.class, args);
	}

}
