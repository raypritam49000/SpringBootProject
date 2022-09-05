package com.user.management.system.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.management.system.entity.User;
import com.user.management.system.repository.UserRepository;
import com.user.management.system.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findByAll() {
		List<User> userList = this.userRepository.findAll();
		LOGGER.info("===>>> Going to UserService findByAll method " + userList);
		return userList;
	}

	public User findById(int id) {
		User user = userRepository.findById(id).get();
		LOGGER.info("===>>> Going to UserService findById method user[" + user + "]");
		return user;
	}

	public User save(User user) {
		LOGGER.info("===>>> Going to UserService save method user[" + user + "]");
		User addUser = this.userRepository.save(user);
		return addUser;
	}

	public Boolean delete(int id) {
		LOGGER.info("===>>> Going to UserService delete method id [" + id + "]");
		this.userRepository.delete(userRepository.findById(id).get());
		return true;
	}

	public User updateUser(int id, User updateUser) {
		LOGGER.info("===>>> Going to UserService updateUser method id [" + id + "] user [" + updateUser + "]");
		User user = userRepository.findById(id).get();
		User updatedUser = null;
		LOGGER.info("===>>> Going to UserService updateUser method user [" + user + "]");
		if (user != null) {
			user.setName(updateUser.getName());
			user.setSalary(updateUser.getSalary());
			user.setCity(updateUser.getCity());
			updatedUser = this.userRepository.save(user);
		}

		return updatedUser;
	}

}
