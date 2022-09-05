package com.ray.pritam.cache.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ray.pritam.cache.entity.Customer;
import com.ray.pritam.cache.services.CustomerService;
import com.ray.pritam.cache.utils.Response;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	public ResponseEntity<Response> getAllCustomer() {
		try {
			List<Customer> customers = customerService.getAllCustomers();
			if (customers == null || customers.size() <= 0 || customers.isEmpty()) {
				return new ResponseEntity<Response>(
						new Response("Customer Not Found", false, "NOT_FOUND", 404, new ArrayList<>()),
						HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Response>(new Response("Customer List", true, "OK", 200, customers),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response(e.getMessage(), false, "INTERNAL_SERVER_ERROR", 502, new ArrayList<>()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Response> getCustomer(@PathVariable("id") Long id) {
		try {
			Customer customer = customerService.getCustomer(id);
			if (customer != null && !customer.getName().equals("") && customer.getName() != null
					&& !customer.getCity().equals("") && customer.getCity() != null && !customer.getEmail().equals("")
					&& customer.getEmail() != null) {

				return new ResponseEntity<Response>(
						new Response("Customer List", true, "OK", 200, Arrays.asList(customer)), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(
						new Response("Customer Not Found", false, "NOT_FOUND", 404, new ArrayList<>()),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response(e.getMessage(), false, "INTERNAL_SERVER_ERROR", 502, new ArrayList<>()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/customers")
	public ResponseEntity<Response> createCustomer(@RequestBody Customer customer) {
		try {
			if (customer != null && !customer.getName().equals("") && customer.getName() != null
					&& !customer.getCity().equals("") && customer.getCity() != null && !customer.getEmail().equals("")
					&& customer.getEmail() != null) {
				Customer createCustomer = customerService.createCustomer(customer);

				return new ResponseEntity<Response>(
						new Response("Customer Created", true, "CREATED", 201, Arrays.asList(createCustomer)),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Response>(
						new Response("All Parameters are required", false, "BAD_REQUEST", 400, new ArrayList<>()),
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response(e.getMessage(), false, "INTERNAL_SERVER_ERROR", 502, new ArrayList<>()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Response> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
		try {
			if (customer != null && !customer.getName().equals("") && customer.getName() != null
					&& !customer.getCity().equals("") && customer.getCity() != null && !customer.getEmail().equals("")
					&& customer.getEmail() != null) {
				Customer updateCustomer = customerService.updateCustomer(id, customer);

				return new ResponseEntity<Response>(
						new Response("Customer Updated", true, "UPDATED", 204, Arrays.asList(updateCustomer)),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Response>(
						new Response("All Parameters are required", false, "BAD_REQUEST", 400, new ArrayList<>()),
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response(e.getMessage(), false, "INTERNAL_SERVER_ERROR", 502, new ArrayList<>()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Response> deleteCustomer(@PathVariable("id") Long id) {
		try {
			Boolean isDeleted = customerService.deleteCustomer(id);

			if (isDeleted) {
				return new ResponseEntity<Response>(new Response("Customer Deleted", true, "OK", 203, Arrays.asList()),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(
						new Response("Error Occure When Delete Customer", false, "BAD_REQUEST", 400, new ArrayList<>()),
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response(e.getMessage(), false, "INTERNAL_SERVER_ERROR", 502, new ArrayList<>()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
