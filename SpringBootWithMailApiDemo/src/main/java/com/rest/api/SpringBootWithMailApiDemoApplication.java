package com.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.rest.api.services.EmailSenderService;

@SpringBootApplication
public class SpringBootWithMailApiDemoApplication {
	
	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithMailApiDemoApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() {
		System.out.println("===>>> Start Event...");
		//emailSenderService.sendSimpleEmail("raypritam49000@gmail.com", "This is Email Body", "This is Email Subject");
		//emailSenderService.sendEmailWithAttachment("raypritam49000@gmail.com", "This is Email Body", "This is Email Subject", "D:\\profile.jpg");
	}

}
