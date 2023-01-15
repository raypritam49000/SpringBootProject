package com.ray.pritam.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.ray.pritam.events.UserCreatedEvent;

@Component
public class UserCreatedListener implements ApplicationListener<UserCreatedEvent> {

	@Override
	public void onApplicationEvent(UserCreatedEvent event) {
		System.out.println(String.format("User created: %s", event.getName()));
	}
}