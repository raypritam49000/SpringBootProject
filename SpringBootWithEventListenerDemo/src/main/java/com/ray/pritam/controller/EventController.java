package com.ray.pritam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ray.pritam.publister.CustomSpringEventPublisher;

@RestController
public class EventController {

	@Autowired
	private CustomSpringEventPublisher eventPublisher;
	
	@GetMapping("/notify/event")
	public void publishEvent() {
		System.out.println("====>>> Controller ");
		eventPublisher.publishCustomEvent("How to you ? Pritam Ray");
		eventPublisher.publishCustomEvent("I am fine.");
	}
}
