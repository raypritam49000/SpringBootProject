package com.cache.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cache.api.entity.Customer;
import com.cache.api.repository.CustomerRepository;
import com.cache.api.services.CustomerServices;

@Service
public class CustomerServicesImpl implements CustomerServices {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) {
		return this.customerRepository.save(customer);
	}

	@CachePut(value = "customers", key = "#customer.id")
	@Override
	public Customer updateCustomer(Long id, Customer customer) {
		Customer existingCust = customerRepository.findById(id).get();
		Customer updateCustomer = null;
		if (existingCust != null) {
			existingCust.setCity(customer.getCity());
			existingCust.setName(customer.getName());
			existingCust.setSalary(customer.getSalary());
			updateCustomer = customerRepository.save(existingCust);
		}
		return updateCustomer;
	}

	@Cacheable(value = "customers", key = "#customer.id")
	@Override
	public Customer getCustomer(Long id) {
		return customerRepository.findById(id).get();
	}

	@Cacheable(value = "customers")
	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@CacheEvict(value = "customers", key = "#customer.id")
	@Override
	public Boolean deleteCustomers(Long id) {
		customerRepository.delete(customerRepository.findById(id).get());
		return true;
	}

}
