package com.rest.api.demo.services;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.rest.api.demo.models.User;

@Service
public class UserService {

	private static final Logger LOGGER = LogManager.getLogger(UserService.class);

	public static List<User> userList = new ArrayList<User>();

	static {
		userList.add(new User(1, "Pritam Ray", "Ropar", "20000"));
		userList.add(new User(2, "Omi Verma", "Prem Nagar", "25000"));
		userList.add(new User(3, "Chandan Kumar", "Asron", "15000"));
	}

	public List<User> findByAll() {
		LOGGER.info("===>>> Going to UserService findByAll method " + userList);
		return userList;
	}

	public User findById(int id) {
		User user = userList.stream().filter(t -> t.getId() == id).findFirst().get();
		LOGGER.info("===>>> Going to UserService findById method user[" + user + "]");
		return user;
	}

	public User save(User user) {
		LOGGER.info("===>>> Going to UserService save method user[" + user + "]");
		userList.add(user);
		return user;
	}

	public Boolean delete(int id) {
		LOGGER.info("===>>> Going to UserService delete method id [" + id + "]");
		for (User user : userList) {
			if (user.getId() == id) {
				userList.remove(user);
			}
		}
		return true;
	}

	public User updateUser(int id, User updateUser) {
		LOGGER.info("===>>> Going to UserService updateUser method id [" + id + "] user ["+updateUser+"]");
		User user = userList.stream().filter(t -> t.getId() == id).findFirst().get();

		LOGGER.info("===>>> Going to UserService updateUser method user ["+user+"]");
		if (user != null) {
			user.setName(updateUser.getName());
			user.setSalary(updateUser.getSalary());
			user.setCity(updateUser.getCity());
		}

		return user;
	}

}
