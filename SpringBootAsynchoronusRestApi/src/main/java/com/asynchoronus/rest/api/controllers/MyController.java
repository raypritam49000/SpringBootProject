package com.asynchoronus.rest.api.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asynchoronus.rest.api.model.Customer;
import com.asynchoronus.rest.api.services.MyService;

@RestController
public class MyController {

	@Autowired
	private MyService service;

	@RequestMapping(value = "/getAsynchData", method = RequestMethod.GET)
	public String getAsynchData() throws InterruptedException, ExecutionException {
		final long startTime = System.currentTimeMillis();
		
		CompletableFuture<List<Customer>> customer1 = service.getCustomer1();
		CompletableFuture<List<Customer>> customer2 = service.getCustomer2();
		CompletableFuture<List<Customer>> customer3 = service.getCustomer3();

		// Wait until they are all done
		CompletableFuture.allOf(customer1, customer2, customer3).join();
		
		final long endTime = System.currentTimeMillis();

		final long duration = (endTime - startTime)/1000;
		return "[startTime : "+startTime+"] [endTime : "+endTime+"] [durationTime : " + duration + " secs ]";
	}

}