package com.asynchoronus.rest.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.asynchoronus.rest.api.model.Customer;

@Service
public class MyService {

	private static List<Customer> customerList = new ArrayList<>();
	
	static {
		customerList.add(new Customer("Pritam Ray", 34));
		customerList.add(new Customer("Amit Kumar", 24));
		customerList.add(new Customer("Raj Kumar", 43));
		customerList.add(new Customer("Omi Verma", 54));
	}

	@PostConstruct
	public void initialize() {
		for (int i = 1; i <= 1000; i++) {
			customerList.add(new Customer("name-" + i, i));
		}
	}

	@Async("asyncExecutor")
	public CompletableFuture<List<Customer>> getCustomer1() throws InterruptedException {
		Thread.sleep(1000L); // Intentional delay
		return CompletableFuture.completedFuture(customerList.subList(1, 500));
	}

	@Async("asyncExecutor")
	public CompletableFuture<List<Customer>> getCustomer2() throws InterruptedException {
		Thread.sleep(2000L); // Intentional delay
		return CompletableFuture.completedFuture(customerList.subList(300, 700));
	}

	@Async("asyncExecutor")
	public CompletableFuture<List<Customer>> getCustomer3() throws InterruptedException {
		Thread.sleep(3000L); // Intentional delay
		return CompletableFuture.completedFuture(customerList.subList(600, 900));
	}

}