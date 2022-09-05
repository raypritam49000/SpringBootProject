package com.ray.pritam.events;

public class UserRemovedEvent {

	public String name;

	public UserRemovedEvent(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
