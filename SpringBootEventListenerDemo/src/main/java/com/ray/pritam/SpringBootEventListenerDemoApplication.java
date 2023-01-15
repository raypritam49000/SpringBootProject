package com.ray.pritam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.ray.pritam.listeners.SpringBuiltInEventsListener;

@SpringBootApplication
@EnableAsync
public class SpringBootEventListenerDemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpringBootEventListenerDemoApplication.class, args);
		SpringApplication springApplication = new SpringApplication(SpringBootEventListenerDemoApplication.class);
		// uncomment below line if you are not using spring.factories
		springApplication.addListeners(new SpringBuiltInEventsListener());
		springApplication.run(args);
	}

}
