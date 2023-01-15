package com.rest.api.demo.controllers;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.demo.models.User;
import com.rest.api.demo.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Home Docker App";
	}

	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		try {
			List<User> users = userService.findByAll();
			if (!users.isEmpty() && users != null && users.size() > 0) {

				LOGGER.info("===>>> Going to UserController inside getAllUsers method " + Map.of("isSuccess", true,
						"message", "User List", "statusCode", 200, "statusMessage", "OK", "data", users));

				return ResponseEntity.ok(Map.of("isSuccess", true, "message", "User List", "statusCode", 200,
						"statusMessage", "OK", "data", users));
			} else {

				LOGGER.error("===>>> Going to UserController inside getAllUsers method " + Map.of("isSuccess", false,
						"message", "User Not Found", "statusCode", 404, "statusMessage", "NOT_FOUND"));

				return ResponseEntity.ok(Map.of("isSuccess", false, "message", "User Not Found", "statusCode", 404,
						"statusMessage", "NOT_FOUND"));
			}
		} catch (Exception e) {

			LOGGER.error("===>>> Going to UserController inside getAllUsers method " + Map.of("isSuccess", false,
					"message", e.getMessage(), "statusCode", 502, "statusMessage", "INTERVAL_SERVER_ERROR"));

			return ResponseEntity.ok(Map.of("isSuccess", false, "message", e.getMessage(), "statusCode", 502,
					"statusMessage", "INTERVAL_SERVER_ERROR"));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") int id) {
		try {
			User user = userService.findById(id);

			if (user != null) {

				LOGGER.info("===>>> Going to UserController inside getUser method " + Map.of("isSuccess", true,
						"message", "User List", "statusCode", 200, "statusMessage", "OK", "data", user));

				return ResponseEntity.ok(Map.of("isSuccess", true, "message", "User List", "statusCode", 200,
						"statusMessage", "OK", "data", user));
			} else {

				LOGGER.error("===>>> Going to UserController inside getUser method " + Map.of("isSuccess", false,
						"message", "User Not Found", "statusCode", 404, "statusMessage", "NOT_FOUND"));

				return ResponseEntity.ok(Map.of("isSuccess", false, "message", "User Not Found", "statusCode", 404,
						"statusMessage", "NOT_FOUND"));
			}
		} catch (Exception e) {

			LOGGER.error("===>>> Going to UserController inside getUser method " + Map.of("isSuccess", false, "message",
					e.getMessage(), "statusCode", 502, "statusMessage", "INTERVAL_SERVER_ERROR"));

			return ResponseEntity.ok(Map.of("isSuccess", false, "message", e.getMessage(), "statusCode", 502,
					"statusMessage", "INTERVAL_SERVER_ERROR"));
		}
	}

	@PostMapping("/")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		try {
			User createUser = userService.save(user);
			if (createUser != null) {

				LOGGER.info("===>>> Going to UserController inside createUser method " + Map.of("isSuccess", true,
						"message", "User Created", "statusCode", 201, "statusMessage", "CREATED", "data", createUser));

				return ResponseEntity.ok(Map.of("isSuccess", true, "message", "User Created", "statusCode", 201,
						"statusMessage", "CREATED", "data", createUser));
			} else {

				LOGGER.error("===>>> Going to UserController inside createUser method " + Map.of("isSuccess", false,
						"message", "All Parameter are Required", "statusCode", 400, "statusMessage", "BAD_REQUEST"));

				return ResponseEntity.ok(Map.of("isSuccess", false, "message", "All Parameter are Required",
						"statusCode", 400, "statusMessage", "BAD_REQUEST"));
			}
		} catch (Exception e) {

			LOGGER.error("===>>> Going to UserController inside createUser method " + Map.of("isSuccess", false,
					"message", e.getMessage(), "statusCode", 502, "statusMessage", "INTERVAL_SERVER_ERROR"));

			return ResponseEntity.ok(Map.of("isSuccess", false, "message", e.getMessage(), "statusCode", 502,
					"statusMessage", "INTERVAL_SERVER_ERROR"));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody User user) {
		try {
			User updateUser = userService.updateUser(id, user);
			if (updateUser != null) {
				LOGGER.info("===>>> Going to UserController inside updateUser method " + Map.of("isSuccess", true,
						"message", "User Update", "statusCode", 201, "statusMessage", "CREATED", "data", updateUser));

				return ResponseEntity.ok(Map.of("isSuccess", true, "message", "User Update", "statusCode", 201,
						"statusMessage", "CREATED", "data", updateUser));
			} else {

				LOGGER.error("===>>> Going to UserController inside updateUser method " + Map.of("isSuccess", false,
						"message", "All Parameter are Required", "statusCode", 400, "statusMessage", "BAD_REQUEST"));

				return ResponseEntity.ok(Map.of("isSuccess", false, "message", "All Parameter are Required",
						"statusCode", 400, "statusMessage", "BAD_REQUEST"));
			}
		} catch (Exception e) {

			LOGGER.error("===>>> Going to UserController inside createUser method " + Map.of("isSuccess", false,
					"message", e.getMessage(), "statusCode", 502, "statusMessage", "INTERVAL_SERVER_ERROR"));

			return ResponseEntity.ok(Map.of("isSuccess", false, "message", e.getMessage(), "statusCode", 502,
					"statusMessage", "INTERVAL_SERVER_ERROR"));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
		try {
			Boolean isDeleted = userService.delete(id);
			if (isDeleted) {
				LOGGER.info("===>>> Going to UserController inside deleteUser method " + Map.of("isSuccess", true,
						"message", "User Deleted", "statusCode", 202, "statusMessage", "DELETED"));

				return ResponseEntity.ok(Map.of("isSuccess", true, "message", "User Deleted", "statusCode", 202,
						"statusMessage", "DELETED"));
			} else {
				LOGGER.error("===>>> Going to UserController inside deleteUser method " + Map.of("isSuccess", false,
						"message", "Error Occure When delete user", "statusCode", 400, "statusMessage", "BAD_REQUEST"));

				return ResponseEntity.ok(Map.of("isSuccess", false, "message", "Error Occure When delete user",
						"statusCode", 400, "statusMessage", "BAD_REQUEST"));
			}
		} catch (Exception e) {
			LOGGER.error("===>>> Going to UserController inside deleteUser method " + Map.of("isSuccess", false,
					"message", e.getMessage(), "statusCode", 502, "statusMessage", "INTERVAL_SERVER_ERROR"));

			return ResponseEntity.ok(Map.of("isSuccess", false, "message", e.getMessage(), "statusCode", 502,
					"statusMessage", "INTERVAL_SERVER_ERROR"));
		}
	}
}
