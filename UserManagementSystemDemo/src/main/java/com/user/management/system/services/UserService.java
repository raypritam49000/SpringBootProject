package com.user.management.system.services;

import java.util.List;

import com.user.management.system.entity.User;

public interface UserService {
	public List<User> findByAll();
	public User findById(int id);
	public User save(User user);
	public Boolean delete(int id);
	public User updateUser(int id, User updateUser);
}
