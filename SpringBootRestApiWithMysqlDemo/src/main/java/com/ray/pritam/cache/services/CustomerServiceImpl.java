package com.ray.pritam.cache.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ray.pritam.cache.entity.Customer;
import com.ray.pritam.cache.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomer(Long id) {
		return customerRepository.findById(id).get();
	}

	@Override
	public Boolean deleteCustomer(Long id) throws Exception {
		Customer customer = customerRepository.findById(id).get();
		if (customer == null) {
			throw new Exception("Customer Not Found");
		} else {
			this.customerRepository.delete(customer);
			return true;
		}
	}

	@Override
	public Customer updateCustomer(Long id, Customer customer) throws Exception {
		Customer existCustomer = customerRepository.findById(id).get();
		Customer updatedCustomer = null;
		if (existCustomer == null) {
			throw new Exception("Customer Not Found");
		} else {
			existCustomer.setCity(customer.getCity());
			existCustomer.setEmail(customer.getEmail());
			existCustomer.setName(customer.getName());
			updatedCustomer = this.customerRepository.save(existCustomer);
			return updatedCustomer;
		}
	}

}
