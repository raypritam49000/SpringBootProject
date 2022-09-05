package com.cache.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cache.api.config.SimpleCacheManager;
import com.cache.api.entity.Customer;
import com.cache.api.repository.CustomerRepository;

@SpringBootApplication
public class SpringBootWithCacheDemoApplication implements ApplicationRunner{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	@Qualifier("cache1")
	private SimpleCacheManager simpleCacheManager;
	
	@Autowired
	@Qualifier("cache2")
	private SimpleCacheManager simpleCacheManager1;
	
	@Autowired
	@Qualifier("cache3")
	private SimpleCacheManager simpleCacheManager2;


	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithCacheDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		for(Customer customer:this.customerRepository.findAll()){
			simpleCacheManager.put(""+customer.getId(),customer);
		}
		
		System.out.println(simpleCacheManager);
		System.out.println(simpleCacheManager.get("1"));
		System.out.println(simpleCacheManager.size());
		System.out.println(simpleCacheManager1);
		System.out.println(simpleCacheManager2);
	}

}
