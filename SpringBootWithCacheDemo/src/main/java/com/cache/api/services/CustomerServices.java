package com.cache.api.services;

import java.util.List;

import com.cache.api.entity.Customer;

public interface CustomerServices {
	public Customer createCustomer(Customer customer);
	public Customer updateCustomer(Long id, Customer customer);
	public Customer getCustomer(Long id);
	public List<Customer> getCustomers();
	public Boolean deleteCustomers(Long id);
}
