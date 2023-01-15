package com.rest.api.controllers;

import java.util.List;
import java.util.Map;

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

import com.rest.api.entity.Employee;
import com.rest.api.services.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employeeDto) {

		Employee createEmployee = this.employeeService.createEmployee(employeeDto);

		return new ResponseEntity<>(createEmployee, HttpStatus.CREATED);
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employeeDto,
			@PathVariable("employeeId") Long employeeId) {

		Employee updatedEmployeeDto = this.employeeService.updateEmployee(employeeId, employeeDto);

		return ResponseEntity.ok(updatedEmployeeDto);
	}

	@GetMapping("/")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = this.employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<Employee> getSingleEmployee(@PathVariable("employeeId") Long employeeId) {
		Employee employee = this.employeeService.getEmployee(employeeId);
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
		this.employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<>(Map.of("message", "Deleted Successfully", "success", true, "status", 204),
				HttpStatus.OK);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> getCountEmployees() {
		Long totalEmployees = this.employeeService.getCountEmployees();
		return ResponseEntity.ok(totalEmployees);
	}

}
