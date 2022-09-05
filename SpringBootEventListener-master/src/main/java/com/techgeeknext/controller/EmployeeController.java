package com.techgeeknext.controller;

import com.techgeeknext.event.EmailEvent;
import com.techgeeknext.publisher.EmailPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	EmailPublisher emailPublisher;

	@GetMapping("/notify/event")
	public void publishEvent() {
		emailPublisher.publishEmailEvent(new EmailEvent("Employee added."));
		emailPublisher.publishMsgEvent("Exception occurred.");
	}
}
