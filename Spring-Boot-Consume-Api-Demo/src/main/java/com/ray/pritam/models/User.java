package com.ray.pritam.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int userId;
	private int id;
	private String title;
	private Boolean completed;

	public User(int userId, String title, Boolean completed) {
		super();
		this.userId = userId;
		this.title = title;
		this.completed = completed;
	}

}
