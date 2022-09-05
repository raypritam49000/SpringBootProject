package com.ray.pritam.cache.services;

import java.util.List;

import com.ray.pritam.cache.entity.Customer;

public interface CustomerService {
	public Customer createCustomer(Customer customer);

	public List<Customer> getAllCustomers();

	public Customer getCustomer(Long id);

	public Boolean deleteCustomer(Long id) throws Exception;

	public Customer updateCustomer(Long id, Customer customer) throws Exception;
}
