package com.telemune.soap.api.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telemune.soap.api.client.interfaces.AddEmployeeRequest;
import com.telemune.soap.api.client.interfaces.AddEmployeeResponse;
import com.telemune.soap.api.client.interfaces.DeleteEmployeeRequest;
import com.telemune.soap.api.client.interfaces.DeleteEmployeeResponse;
import com.telemune.soap.api.client.interfaces.GetEmployeeByIdRequest;
import com.telemune.soap.api.client.interfaces.GetEmployeeResponse;
import com.telemune.soap.api.client.interfaces.UpdateEmployeeRequest;
import com.telemune.soap.api.client.interfaces.UpdateEmployeeResponse;
import com.telemune.soap.api.client.service.EmployeeService;

@RestController
public class EmployeeClientController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/addEmployee")
	public AddEmployeeResponse addEmployee(@RequestBody AddEmployeeRequest request) {
		return employeeService.addEmployee(request);
	}

	@GetMapping("/findEmployeeById")
	public GetEmployeeResponse getEmployeeById(@RequestBody GetEmployeeByIdRequest employeeByIdRequest) {
		return employeeService.findEmployeeById(employeeByIdRequest);
	}
	
	@DeleteMapping("/deleteEmployeeById")
	public DeleteEmployeeResponse deleteEmployeeById(@RequestBody DeleteEmployeeRequest deleteEmployeeRequest) {
		return employeeService.deleteEmployeeById(deleteEmployeeRequest);
	}
	
	@PutMapping("/updateEmployeeById")
	public UpdateEmployeeResponse updateEmployeeById(@RequestBody UpdateEmployeeRequest updateEmployeeRequest) {
		return employeeService.updateEmployeeById(updateEmployeeRequest);
	}

}
