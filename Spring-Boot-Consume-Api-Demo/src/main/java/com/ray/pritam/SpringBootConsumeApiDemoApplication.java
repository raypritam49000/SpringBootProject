package com.ray.pritam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ray.pritam.models.User;
import com.ray.pritam.services.ConsumeApiService;

@SpringBootApplication
public class SpringBootConsumeApiDemoApplication implements CommandLineRunner{
	
	@Autowired
	private ConsumeApiService consumeApiService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConsumeApiDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(consumeApiService.getUsers());
		System.out.println(consumeApiService.findById(1));
		System.out.println(consumeApiService.deleteById(1));
		System.out.println(consumeApiService.findById(1));
		System.out.println(consumeApiService.updateUser(1, new User(10, "Java Book",true)));
	}

}
