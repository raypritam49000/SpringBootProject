package com.cache.api.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cache.api.entity.Customer;
import com.cache.api.services.CustomerServices;
import com.cache.api.utils.Response;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerServices customerService;

	@GetMapping
	public ResponseEntity<Response> getAllCustomers() {
		try {
			List<Customer> customers = customerService.getCustomers();

			if (!customers.isEmpty() && customers != null && customers.size() > 0) {
				return new ResponseEntity<Response>(new Response("SUCCESS", "200", true, "Customer List", customers),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(
						new Response("NOT_FOUND", "404", false, "Customer Not Found", customers), HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response(e.getMessage(), "500", false, "Internal Server Error", new ArrayList<>()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getCustomer(@PathVariable("id") Long id) {
		try {

			if (id > 0) {
				return new ResponseEntity<Response>(
						new Response("BAD_REQUEST", "401", false, "BAD_REQUEST", Arrays.asList()),
						HttpStatus.BAD_REQUEST);
			}

			Customer customer = customerService.getCustomer(id);

			if (customer != null && !customer.getCity().equals("") && !customer.getName().equals("")
					&& !customer.getSalary().equals("")) {

				return new ResponseEntity<Response>(
						new Response("SUCCESS", "200", true, "Customer List", Arrays.asList(customer)), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(
						new Response("NOT_FOUND", "404", false, "Customer Not Found", Arrays.asList(customer)),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response(e.getMessage(), "500", false, "Internal Server Error", new ArrayList<>()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<Response> createCustomer(@RequestBody Customer customer) {
		try {

			if (customer != null && !customer.getCity().equals("") && !customer.getName().equals("")
					&& !customer.getSalary().equals("")) {
				Customer createCustomer = customerService.createCustomer(customer);

				if (createCustomer != null && !createCustomer.getCity().equals("")
						&& !createCustomer.getName().equals("") && !createCustomer.getSalary().equals("")) {
					return new ResponseEntity<Response>(
							new Response("CREATED", "201", true, "Customer List", Arrays.asList(createCustomer)),
							HttpStatus.CREATED);
				} else {
					return new ResponseEntity<Response>(
							new Response("BAD_REQUEST", "401", false, "BAD_REQUEST", Arrays.asList()),
							HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<Response>(
						new Response("BAD_REQUEST", "401", false, "All Parameters are required", Arrays.asList()),
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response(e.getMessage(), "500", false, "Internal Server Error", new ArrayList<>()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
		try {

			if (id > 0) {
				return new ResponseEntity<Response>(
						new Response("BAD_REQUEST", "401", false, "BAD_REQUEST", Arrays.asList()),
						HttpStatus.BAD_REQUEST);
			}

			if (customer != null && !customer.getCity().equals("") && !customer.getName().equals("")
					&& !customer.getSalary().equals("")) {
				Customer updateCustomer = customerService.updateCustomer(id, customer);

				if (updateCustomer != null && !updateCustomer.getCity().equals("")
						&& !updateCustomer.getName().equals("") && !updateCustomer.getSalary().equals("")) {
					return new ResponseEntity<Response>(
							new Response("UPDATED", "203", true, "Customer Updated", Arrays.asList(updateCustomer)),
							HttpStatus.CREATED);
				} else {
					return new ResponseEntity<Response>(
							new Response("BAD_REQUEST", "401", false, "BAD_REQUEST", Arrays.asList()),
							HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<Response>(
						new Response("BAD_REQUEST", "401", false, "All Parameters are required", Arrays.asList()),
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response(e.getMessage(), "500", false, "Internal Server Error", new ArrayList<>()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteCustomer(@PathVariable("id") Long id) {
		try {

			if (id > 0) {
				return new ResponseEntity<Response>(
						new Response("BAD_REQUEST", "401", false, "BAD_REQUEST", Arrays.asList()),
						HttpStatus.BAD_REQUEST);
			}

			Boolean isDeleted = customerService.deleteCustomers(id);

			if (isDeleted) {
				return new ResponseEntity<Response>(
						new Response("DELETED", "202", true, "Customer Delated", Arrays.asList()), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(
						new Response("BAD_REQUEST", "401", false, "BAD_REQUEST", Arrays.asList()),
						HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response(e.getMessage(), "500", false, "Internal Server Error", new ArrayList<>()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
