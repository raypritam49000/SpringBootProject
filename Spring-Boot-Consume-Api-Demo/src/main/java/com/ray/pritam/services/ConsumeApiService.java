package com.ray.pritam.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import com.ray.pritam.models.User;

@HttpExchange(url = "/todos")
public interface ConsumeApiService {

	@GetExchange(url = "/")
	public List<User> getUsers();

	@GetExchange(url = "/{id}")
	public User findById(@PathVariable("id") int id);

	@PostExchange(url = "/")
	public User createUser(@RequestBody User user);

	@PutExchange(url = "/{id}")
	public User updateUser(@PathVariable("id") int id, @RequestBody User user);

	@DeleteExchange(url = "/{id}")
	public User deleteById(@PathVariable("id") int id);
}
