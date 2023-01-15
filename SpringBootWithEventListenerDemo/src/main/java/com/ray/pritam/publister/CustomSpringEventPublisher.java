package com.ray.pritam.publister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.ray.pritam.event.CustomSpringEvent;

@Component
public class CustomSpringEventPublisher {
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public void publishCustomEvent(final String message) {
		System.out.println("Publishing custom event. ");
		CustomSpringEvent customSpringEvent = new CustomSpringEvent(this, message);
		applicationEventPublisher.publishEvent(customSpringEvent);
	}
	
}